package com.gas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int leadingUser;
    private String location;
    private String phone;
    private double freeGasNumber;

    // @OneToMany
    // @JoinColumn(name = "id", table = "user")
    // private List<User> users;

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

    public int getLeadingUser() {
        return leadingUser;
    }

    public void setLeadingUser(int leadingUser) {
        this.leadingUser = leadingUser;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getFreeGasNumber() {
        return freeGasNumber;
    }

    public void setFreeGasNumber(double freeGasNumber) {
        this.freeGasNumber = freeGasNumber;
    }
}
