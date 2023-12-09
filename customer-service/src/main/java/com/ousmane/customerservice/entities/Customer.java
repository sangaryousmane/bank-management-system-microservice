package com.ousmane.customerservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ousmane.customerservice.external.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity(name = "Customer")
@Table(name = "customers")
@AllArgsConstructor
@Builder @NoArgsConstructor
public class Customer {

    @Id
    private Integer customerId;

    private String customerName;
    private String customerPhone;
    private String email;
    private String city;
    private Double withdrawalAmount;

    @Transient
    private List<Account> accounts;


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", withdrawalAmount=" + withdrawalAmount +
                ", accounts=" + accounts +
                '}';
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
