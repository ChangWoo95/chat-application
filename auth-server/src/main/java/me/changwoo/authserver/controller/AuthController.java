package me.changwoo.authserver.controller;

import lombok.RequiredArgsConstructor;
import me.changwoo.authserver.protocol.request.SignUpRequest;
import me.changwoo.authserver.protocol.request.LoginRequest;
import me.changwoo.authserver.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final MemberService service;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        return service.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }


}
