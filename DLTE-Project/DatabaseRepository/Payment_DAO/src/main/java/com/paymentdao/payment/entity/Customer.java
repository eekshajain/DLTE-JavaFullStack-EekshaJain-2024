package com.paymentdao.payment.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

public class Customer implements UserDetails {
    @NotNull(message = "{customer.id.null}")
    private Integer customerId;

    @NotNull(message = "{customer.name.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{customer.name}")
    private String customerName;

    @NotNull(message = "{customer.address.null}")
    @Pattern(regexp = "^\\d+\\s[a-zA-Z]+\\s[a-zA-Z]+", message = "{customer.address}")
    private String customerAddress;

    @NotNull(message = "{customer.status.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{customer.status}")
    private String customerStatus;

    @NotNull(message = "{customer.Contact.null}")
   // @Pattern(regexp = "^\\d{10}$", message = "{customer.contact}")
    private Long customerContact;

    @NotNull(message = "{customer.username.null}")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "{customer.username}")
    private String username;

    @NotNull(message = "{customer.password.null}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "{customer.password}")
    private String password;

    private int attempts;
    private final int maxAttempts=3;

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(@NotNull(message = "{customer.id.null}") Integer customerId, @NotNull(message = "{customer.name.null}") @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{customer.name}") String customerName, @NotNull(message = "{customer.address.null}") @Pattern(regexp = "^\\d+\\s[a-zA-Z]+\\s[a-zA-Z]+", message = "{customer.address}") String customerAddress, @NotNull(message = "{customer.status.null}") @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{customer.status}") String customerStatus, @NotNull(message = "{customer.Contact.null}") Long customerContact, @NotNull(message = "{customer.username.null}") @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "{customer.username}") String username, @NotNull(message = "{customer.password.null}") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "{customer.password}") String password, int attempts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
        this.attempts = attempts;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
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
