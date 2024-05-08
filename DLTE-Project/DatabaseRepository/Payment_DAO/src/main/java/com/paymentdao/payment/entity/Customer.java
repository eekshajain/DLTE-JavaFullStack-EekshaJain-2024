package com.paymentdao.payment.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class Customer implements UserDetails {

    private Integer customerId;

    private String customerName;

    private String customerAddress;

    private String customerStatus;

    private Long customerContact;

    private String username;

    private String password;

    private int attempts;
    private final int maxAttempts=3;

    public Integer getCustomerId() {

        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }



    public Customer() {
    }



    public String getCustomerName() {
        return customerName;
    }


    public String getCustomerAddress() {
        return customerAddress;
    }


    public String getCustomerStatus() {
        return customerStatus;
    }


    public Long getCustomerContact() {
        return customerContact;
    }



    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
