package com.groundbnb.dto;

public class CustomerSignupDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CharSequence getPassword() {
        return password;
    }
}
