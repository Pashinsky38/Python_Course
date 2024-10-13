package com.example.pythoncourse;
public class UserDetails {
    // Member variables
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    // Constructor for register
    public UserDetails(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    // 2nd constructor for login
    public UserDetails(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // Method to return a string representation of UserDetails
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nPassword: " + password + "\nPhone Number: " + phoneNumber;
    }
    // Getter for name
    public String getName() {
        return name;
    }
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
    // Getter for email
    public String getEmail() {
        return email;
    }
    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
    // Getter for password
    public String getPassword() {
        return password;
    }
    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    // Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
