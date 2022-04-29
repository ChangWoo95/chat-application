package me.changwoo.authserver.repository.entity.mapping;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendsId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name="MEMBER_ID")
    private String memberId;

    @EqualsAndHashCode.Include
    @Column(name = "FOLLOWER_ID")
    private String followerId;

    @Builder
    public FriendsId(String memberId, String followerId) {
        this.memberId = memberId;
        this.followerId = followerId;
    }
}
