package me.changwoo.authserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.changwoo.authserver.protocol.request.FriendRequest;
import me.changwoo.authserver.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log
public class MemberService {

    private final MemberRepository memberRepository;
    private final ResponseProvider responseProvider;


    @Transactional(readOnly = true)
    public ResponseEntity<?> getFriends(String id) {
        List<String> friendList = memberRepository.findFriendListById(id);
        return responseProvider.successMessage(true, friendList);
    }
}
