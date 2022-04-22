package me.changwoo.authserver.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {
    private String id;
    private String email;
    private String password;
    private String nickName;
    private String phoneNumber;

    @Builder
    public MemberDto(String id, String email, String password, String nickName, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }
}
