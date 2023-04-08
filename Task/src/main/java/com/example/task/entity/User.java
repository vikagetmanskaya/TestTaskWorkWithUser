package com.example.task.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "User ID")
    private long id;
    @Column(name = "username")
    @ApiModelProperty(notes = "Username")
    String username;
    @Column(name = "email")
    @ApiModelProperty(notes = "User Email")
    String email;
    @Column(name = "password")
    @ApiModelProperty(notes = "User Password")
    String password;
    @Column(name = "phone_number")
    @ApiModelProperty(notes = "User Phone Number")
    String phoneNumber;
    @Column(name = "photo")
    @ApiModelProperty(notes = "User Photo")
    String urlPhoto;
    @ApiModelProperty(notes = "User Status")
    @Column(name = "status")
    Boolean status;

    public User() {
    }

    public User(long id, String username, String email, String phoneNumber, String urlPhoto, Boolean status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.urlPhoto = urlPhoto;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' + "\n" +
                "email='" + email + '\'' + "\n" +
                "password='" + password + '\'' + "\n" +
                "phoneNumber='" + phoneNumber + '\'' + "\n" +
                "urlPhoto='" + urlPhoto + '\'' + "\n" +
                '}';
    }
}
