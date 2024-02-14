package com.example;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {
    private String username;
    private String password;

    @Inject
    private UserApplicationBean userApplicationBean; // This will be injected by the CDI container


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String login() {
        userApplicationBean.addUser(this.username);

        // Redirect to the page showing the user list
        return "usersList?faces-redirect=true";
    }
}
