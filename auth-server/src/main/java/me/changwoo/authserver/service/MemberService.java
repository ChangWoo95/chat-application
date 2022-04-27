package me.changwoo.authserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.changwoo.authserver.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Log
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> getFriends() {
        return null;
    }
}
