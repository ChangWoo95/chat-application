package me.changwoo.authserver.controller;

import lombok.RequiredArgsConstructor;
import me.changwoo.authserver.protocol.request.FriendRequest;
import me.changwoo.authserver.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @PostMapping("/friend/add")
//    public ResponseEntity<?> addFriend() {
//
//    }

    @GetMapping("/friends/list")
    public ResponseEntity<?> getFriends(@RequestParam(value="id") String id) {
        return memberService.getFriends(id);
//        return null;
    }

}
