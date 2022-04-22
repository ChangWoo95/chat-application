package me.changwoo.authserver.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "USER_ID")
    private String id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NICK_NAME", nullable = false)
    private String nickName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Builder
    public Member(String id, String email, String nickName, String password, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
