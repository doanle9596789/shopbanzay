package com.example.shopbangiay.service.user;
import com.example.shopbangiay.model.DTO.ICountRole;
import com.example.shopbangiay.model.Users;
import com.example.shopbangiay.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface IUserService extends IGeneralService<Users>, UserDetailsService {
    Users findByUsername(String name);

    Iterable<ICountRole> getRoleNumber();

    Boolean existsByUsername(String username);

    Iterable<Users> findAll();

    Optional<Users> findById(Long id);

    Users save(Users users);

    void remove(Long id);

    Optional<Users> findByVerificationToken(String verificationToken);

    void verifyAccount(String verificationToken);

    void sendVerificationEmail(Users user);

    Boolean existsByEmail(String email);


}
