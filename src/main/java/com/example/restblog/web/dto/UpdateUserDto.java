package com.example.restblog.web.dto;

public class UpdateUserDto {
    private long id;
    private String username;
    private String email;


    public UpdateUserDto(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }


    public UpdateUserDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdateUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
