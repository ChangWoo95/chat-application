package me.changwoo.authserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.changwoo.authserver.dto.MemberDto;
import me.changwoo.authserver.protocol.request.LoginRequest;
import me.changwoo.authserver.protocol.request.SignUpRequest;
import me.changwoo.authserver.protocol.response.ErrorResponse;
import me.changwoo.authserver.protocol.response.ResultResponse;
import me.changwoo.authserver.repository.MemberRepository;
import me.changwoo.authserver.repository.entity.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisProvider redisProvider;
    private final ResponseProvider responseProvider;

    public ResponseEntity<?> signUp(SignUpRequest signUpRequest) {
        String uuid = UUID.randomUUID().toString();
        String password = passwordEncoder.encode(signUpRequest.getPassword());

        try {
            memberRepository.save(Member.builder()
                    .id(uuid)
                    .password(password)
                    .email(signUpRequest.getEmail())
                    .nickName(signUpRequest.getNickName())
                    .phoneNumber(signUpRequest.getPhoneNumber())
                    .build());

            return responseProvider.successMessage(true, null);
        } catch(NoResultException e) {
            return responseProvider.errorMessage(false,"SIGNUP_FAIL::NO RESULT", "회원가입에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> login(LoginRequest request) {

            log.info("login service started...");
            String email = request.getEmail();
            String password = request.getPassword();
            MemberDto memberDto = memberRepository.findByEmail(email);

            if(memberDto != null) {
                return responseProvider.errorMessage(false, "LOGIN_FAIL::NOUSER", "이메일 정보가 잘못되었습니다.", HttpStatus.NOT_FOUND);
            }

            if(passwordEncoder.matches(password, memberDto.getPassword())) {
                log.info("Successful login");
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Access-token", tokenProvider.createAccessToken(memberDto.getId()));
                redisProvider.setRedis(memberDto.getId(), tokenProvider.createRefreshToken(memberDto.getId()));
                return responseProvider.successMessage(true, null, httpHeaders);
            }

            return responseProvider.errorMessage(false, "LOGIN_FAIL::NO MATCH PASSWORD", "비밀번호가 잘못되었습니다.", HttpStatus.NOT_FOUND);
    }
}