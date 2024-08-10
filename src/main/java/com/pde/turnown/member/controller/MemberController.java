package com.pde.turnown.member.controller;

import com.pde.turnown.common.ResponseDTO;
import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /* 회원가입 */
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

    /* 아이디 찾기 */
    @PostMapping("/login/findID")
    public ResponseEntity<ResponseDTO> findMemberID(@RequestBody MemberDTO findMemberEmail) {
        String findId = memberService.findMemberByEmail(findMemberEmail.getMemberEmail()).getMemberID();

        if(findId.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NO_CONTENT, "아이디 미발견", null));
        }else {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "아이디 발견", findId));
        }
    }

    /* 비밀번호 찾기 */
    @PostMapping("/login/findPW")
    public ResponseEntity<ResponseDTO> findMemberPW(@RequestBody MemberDTO memberInfo) {
        int min = 100000;
        int max = 999999;
        int authCode = (int) (Math.random() * (max - min + 1)) + min;

        Optional<MemberDTO> member = memberService.findMember(memberInfo.getMemberID());
        member.get().setEmailCode(authCode);

        MemberDTO memberInfoDTO = member.get();

        if(member.get().getMemberID().isEmpty()) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NO_CONTENT, "아이디가 존재하지 않습니다."));
        }else {
            if(member.get().getMemberEmail().equals(memberInfo.getMemberEmail())) {
                memberService.findMemberPW(memberInfoDTO);

                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "인증 코드 발송"));
            }else {
                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NO_CONTENT, "회원정보가 일치하지 않습니다."));
            }
        }
    }

    /* 인증번호 확인 */
    @PostMapping("/login/confirmCode")
    public ResponseEntity<ResponseDTO> confirmEmailCode(@RequestBody MemberDTO codeMember) {
        int saveCode = memberService.findMemberByEmail(codeMember.getMemberEmail()).getEmailCode();

        if(saveCode == codeMember.getEmailCode()) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "인증 성공", 1));
        }else {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NOT_ACCEPTABLE, "인증 실패", 0));
        }
    }
}
