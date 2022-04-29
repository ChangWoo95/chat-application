package me.changwoo.authserver.repository;

import me.changwoo.authserver.repository.entity.Friends;
import me.changwoo.authserver.repository.entity.Member;
import me.changwoo.authserver.repository.entity.mapping.FriendsId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FriendsRepository friendsRepository;

    @Test
    public void memberInsertionAndSelectionTest() {
        for(int i = 0 ; i < 100000; ++i) {
            if(i == 99999) {
                Member member = Member.builder()
                        .id(String.valueOf(i))
                        .email("test@gmail.com")
                        .nickName("asd")
                        .password("test")
                        .phoneNumber("010-1111-1111")
                        .build();
                memberRepository.save(member);
            } else {
                    Member member = Member.builder()
                            .id(String.valueOf(i))
                            .email("test")
                            .nickName("asd")
                            .password("test")
                            .phoneNumber("010-1111-1111")
                            .build();
                memberRepository.save(member);
            }
        }
//        List<Member> test = memberRepository.findAll();
//        assertEquals(100000, test.size());
    }

    @Test
    public void friendsInsertionAndSelectionTest() {
        for(int i = 3; i < 10000; ++i) {
            FriendsId friendsId = FriendsId.builder()
                    .memberId(String.valueOf(2))
                    .followerId(String.valueOf(i))
                    .build();
            Friends friend = Friends.builder()
                                    .friendsId(friendsId)
                                    .build();
            friendsRepository.save(friend);
        }
    }
}