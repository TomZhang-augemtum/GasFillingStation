package com.gas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private String phone;
    private double freeGasNumber;
    // private int leadingUser;

    // @OneToMany
    // @JoinColumn(name = "companyid", table = "user")
    // private List<User> users;
    //
    // public List<User> getUsers() {
    // return users;
    // }
    //
    // public void setUsers(List<User> users) {
    // this.users = users;
    // }

    @OneToOne
    @JoinColumn(name = "leadingUser")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // public int getLeadingUser() {
    // return leadingUser;
    // }
    //
    // public void setLeadingUser(int leadingUser) {
    // this.leadingUser = leadingUser;
    // }

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
