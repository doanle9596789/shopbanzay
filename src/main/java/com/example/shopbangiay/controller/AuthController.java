package com.example.shopbangiay.controller;

import com.example.shopbangiay.model.DTO.ICountRole;
import com.example.shopbangiay.model.DTO.JwtResponse;
import com.example.shopbangiay.model.DTO.request.SignInForm;
import com.example.shopbangiay.model.DTO.request.SignUpForm;
import com.example.shopbangiay.model.DTO.response.ResponseMessage;
import com.example.shopbangiay.model.Roles;
import com.example.shopbangiay.model.Users;
import com.example.shopbangiay.service.jwt.JwtService;
import com.example.shopbangiay.service.role.IRoleService;
import com.example.shopbangiay.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpForm user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Tên người dùng đã tồn tại! Vui lòng thử lại !"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại! Vui lòng thử lại !"), HttpStatus.BAD_REQUEST);
        }
        Users users = user.toUser();
        Set<String> roleNames = user.getRoles();
        Set<Roles> roles = roleService.getRolesByName(roleNames);
        users.setRoles(roles);
        users.setAvatar("https://cdn-icons-png.flaticon.com/512/149/149071.png");
        userService.save(users);
        userService.sendVerificationEmail(users);
        return new ResponseEntity<>(new ResponseMessage("Vui lòng truy cập email để xác nhận đăng ký"), HttpStatus.OK);
    }

    @GetMapping("/verify")
    public RedirectView verifyAccount(@RequestParam String token) {
        userService.verifyAccount(token);
        return new RedirectView("http://localhost:3000/login?success=true");
    }

    /**
     * Xác thực thông tin đăng nhập và tạo JWT token cho người dùng.
     *
     * @param user thông tin đăng nhập của người dùng
     * @return ResponseEntity chứa thông tin JWT token và thông tin người dùng
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInForm user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findByUsername(user.getUsername());

            if (!currentUser.isVerified()) {
                return new ResponseEntity<>(new ResponseMessage("Tài khoản chưa được xác nhận vui lòng truy cập vào email đăng ký để kích hoạt tài khoản"),HttpStatus.ACCEPTED);
            }
            JwtResponse jwtResponse = new JwtResponse(jwt, currentUser.getId(), currentUser.getName(),
                    currentUser.getAvatar(), currentUser.getUserName(), userDetails.getAuthorities());
            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseMessage("Sai tài khoản hoặc mật khẩu !"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users updatedUser) {
        Optional<Users> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            Users existingUser = userOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPhone(updatedUser.getPhone());
            if (updatedUser.getAvatar() != null) {
                existingUser.setAvatar(updatedUser.getAvatar());
            }

            return new ResponseEntity<>(userService.save(existingUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("change/{id}")
    public ResponseEntity<?> changePass(@PathVariable Long id, @RequestBody Users users) {
        Optional<Users> usersOptional = userService.findById(id);
        if (usersOptional.isPresent()) {
            Users existingUser = usersOptional.get();
            if (!existingUser.getPassWord().equals(users.getOldPassword())) {
                return new ResponseEntity<>(new ResponseMessage("Mật khẩu hiện tại không chính xác."), HttpStatus.BAD_REQUEST);
            }
            if (users.getPassWord().equals(users.getConfirmPassword())) {
                existingUser.setPassWord(users.getPassWord());
                userService.save(existingUser);
                return new ResponseEntity<>(new ResponseMessage("Đổi mật khẩu thành công"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Mật khẩu và xác nhận mật khẩu không khớp."), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Người dùng không tồn tại"), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        Optional<Users> userOptional = userService.findById(id);
        return userOptional.map(users
                -> new ResponseEntity<>(users, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/admin")
    public ResponseEntity<Iterable<ICountRole>> hello() {
        Iterable<ICountRole> iCountRoles = userService.getRoleNumber();
        return new ResponseEntity<>(iCountRoles, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Users>> listUsers(){
        Iterable<Users> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}