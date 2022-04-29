package me.changwoo.authserver.repository.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.changwoo.authserver.repository.entity.mapping.FriendsId;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "friends")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friends extends BaseTimeEntity {

    @EmbeddedId
    private FriendsId friendsId;

    @Builder
    public Friends(FriendsId friendsId) {
        this.friendsId = friendsId;
    }
}
