package me.changwoo.authserver.protocol.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
