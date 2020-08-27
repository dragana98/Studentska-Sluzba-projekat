package com.example.proba1;

public class ForgottenPassDTO {

    String username;

    public ForgottenPassDTO()
    {

    }

    public ForgottenPassDTO(String username)
    {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
