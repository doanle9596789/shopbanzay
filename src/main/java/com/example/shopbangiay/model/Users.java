package com.example.shopbangiay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity

public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String userName;
    private String passWord;
    private String oldPassword;
    private String confirmPassword;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private boolean isVerified;
    @JsonIgnore
    private String verificationToken;
    @OneToOne
    private CartItem cart;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_role")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> roles;
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Zay> zay;

    public Users() {
    }
    public Users(String username, String password, String email) {
        this.userName = username;
        this.passWord = password;
        this.email = email;
        this.isVerified = false;
        this.verificationToken = UUID.randomUUID().toString();
    }

    public Users(Long id, String name, Date dateOfBirth, String userName, String passWord, String oldPassword, String confirmPassword, String avatar, String phone, String email, String address, boolean isVerified, String verificationToken, Set<Roles> roles, Set<Zay> zay) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.userName = userName;
        this.passWord = passWord;
        this.oldPassword = oldPassword;
        this.confirmPassword = confirmPassword;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.isVerified = isVerified;
        this.verificationToken = verificationToken;
        this.roles = roles;
        this.zay = zay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Zay> getZay() {
        return zay;
    }

    public void setZay(Set<Zay> zay) {
        this.zay = zay;
    }
}