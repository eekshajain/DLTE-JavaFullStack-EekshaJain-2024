//package com.payment.webservices.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class MyBankUsers implements UserDetails {
//    private String name;
//    private String username;
//    private String password;
//    private String email;
//    private long contact;
//    private int status;
//    private int attempts;
//
////
////    public String getRole() {
////        return role;
////    }
////
////    public void setRole(String role) {
////        this.role = role;
////    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getAttempts() {
//        return attempts;
//    }
//
//    public void setAttempts(int attempts) {
//        this.attempts = attempts;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
////        authorities.add(new SimpleGrantedAuthority(role));
////        return authorities;
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public long getContact() {
//        return contact;
//    }
//
//    public void setContact(long contact) {
//        this.contact = contact;
//    }
//
//
//}