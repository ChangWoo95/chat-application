package me.changwoo.kafkachatserver.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "CHATROOM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity implements Persistable<String> {

    @Id
    @Column(name = "CHATROOM_ID")
    private String roomId;

    @Override
    public boolean isNew() {
        return this.getCraetedTime() == null;
    }

    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String getId() {
        return null;
    }
}
