package com.apiVerse.UserCase.DTO;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String phoneNumber;
    private String password;
}

