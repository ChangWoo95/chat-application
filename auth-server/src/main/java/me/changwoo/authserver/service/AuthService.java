package me.changwoo.authserver.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.java.Log;
import me.changwoo.authserver.dto.MemberDto;
import me.changwoo.authserver.protocol.request.LoginRequest;
import me.changwoo.authserver.protocol.request.SignUpRequest;
import me.changwoo.authserver.repository.MemberRepository;
import me.changwoo.authserver.repository.entity.Member;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
//    private final RedisProvider redisProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private ValueOperations<String, Object> opsAuthInfo;
    private final ResponseProvider responseProvider;

    @PostConstruct
    private void init() {
        opsAuthInfo = redisTemplate.opsForValue();
    }

    public ResponseEntity<?> signUp(SignUpRequest signUpRequest) {
        String uuid = UUID.randomUUID().toString();
        String password = passwordEncoder.encode(signUpRequest.getPassword());

        try {
            MemberDto memberDto = memberRepository.findByEmail(signUpRequest.getEmail());
            if(memberDto != null) {
                return responseProvider.errorMessage(false, "SIGNUP_FAIL::EXIST", "이미 있는 회원입니다.", HttpStatus.NOT_FOUND);
            }
            Member member = memberRepository.save(Member.builder()
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

            if(memberDto == null) {
                return responseProvider.errorMessage(false, "LOGIN_FAIL::NOUSER", "이메일 정보가 잘못되었습니다.", HttpStatus.NOT_FOUND);
            }

            if(passwordEncoder.matches(password, memberDto.getPassword())) {
                log.info("Successful login");
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("access-token", tokenProvider.createAccessToken(memberDto.getId()));
//                redisProvider.setRedis(memberDto.getId(), tokenProvider.createRefreshToken(memberDto.getId()));
                opsAuthInfo.set(memberDto.getId(), tokenProvider.createRefreshToken(memberDto.getId()));

                log.info("레디스 확인 작업");
                log.info((String)opsAuthInfo.get(memberDto.getId()));


                return responseProvider.successMessage(true, null, httpHeaders);
            }

            return responseProvider.errorMessage(false, "LOGIN_FAIL::NO MATCH PASSWORD", "비밀번호가 잘못되었습니다.", HttpStatus.NOT_FOUND);
    }
}
