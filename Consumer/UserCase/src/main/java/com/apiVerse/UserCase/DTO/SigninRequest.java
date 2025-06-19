package com.apiVerse.UserCase.DTO;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
