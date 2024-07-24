package com.pde.turnown.member.controller;

import com.pde.turnown.common.ResponseDTO;
import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/login/findID")
    public ResponseEntity<ResponseDTO> findMemberID(@RequestBody MemberDTO findMemberEmail) {
        String findId = memberService.findMemberID(findMemberEmail.getMemberEmail());

        if(findId.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NO_CONTENT, "아이디 미발견", null));
        }else {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "아이디 발견", findId));
        }
    }

    /* 비밀번호 찾기 */
//    @GetMapping("/login/findPW")
//    public ResponseEntity<ResponseDTO> findMemberPW(@RequestBody MemberDTO memberInfo) {
//        Optional<MemberDTO> member = memberService.findMember(memberInfo.getMemberID());
//
//        if(member.get().getMemberID() == null) {
//            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NO_CONTENT, "아이디가 존재하지 않습니다.", null));
//        }else {
//            // 가입된 이메일
//            if(member.get().getMemberEmail().equals(memberInfo.getMemberEmail())) {
//                memberService.sendEmailCode(memberInfo.getMemberID());
//            }
//        }
//    }
}
