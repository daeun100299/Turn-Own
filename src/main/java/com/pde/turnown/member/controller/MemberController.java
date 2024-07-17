package com.pde.turnown.member.controller;

import com.pde.turnown.common.ResponseDTO;
import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signupMember(@RequestBody MemberDTO newMember) {
        newMember.setMemberPW(bCryptPasswordEncoder.encode(newMember.getMemberPW()));
        memberService.signupMember(newMember);

        if(memberService.signupMember(newMember).getMemberID().isEmpty()) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NO_CONTENT, "회원가입 실패", null));
        }else {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원가입 성공", newMember));
        }
    }
}
