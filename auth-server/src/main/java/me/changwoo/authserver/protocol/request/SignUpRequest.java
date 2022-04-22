package me.changwoo.authserver.protocol.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String email;
    private String password;
    private String nickName;
    private String phoneNumber;
}
