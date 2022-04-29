package me.changwoo.authserver.repository.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MEMBER", indexes = @Index(name = "IDX_MEMBER_EMAIL", columnList = "email"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity implements Persistable<String> {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NICK_NAME", nullable = false)
    private String nickName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name="FRIEND_LIST")
    @JsonRawValue
    private String friendList;

    @Column(name="CHATROOM_LIST")
    @JsonRawValue
    private String chatroomList;

    @Builder
    public Member(String id, String email, String nickName, String password, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", friendList='" + friendList + '\'' +
                ", chatroomList='" + chatroomList + '\'' +
                '}';
    }

    @Override
    public boolean isNew() {
        return this.getCraetedTime() == null;
    }
}
