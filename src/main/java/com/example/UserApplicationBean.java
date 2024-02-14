package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserApplicationBean {
    List<String> users = new ArrayList<>(); //

    public UserApplicationBean() {
//        users.add("USER1");
    }
    public void addUser(String username) {
        users.add(username);
    }

    public List<String> getUsers() {
        return users;
    }
}