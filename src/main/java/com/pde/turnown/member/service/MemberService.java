package com.pde.turnown.member.service;

import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.entity.Member;
import com.pde.turnown.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final JavaMailSender mailSender;

    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper, JavaMailSender mailSender) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
    }

    public MemberDTO signupMember(MemberDTO newMember) {
        Member memberEntity = Member.builder()
                .memberID(newMember.getMemberID())
                .memberPW(newMember.getMemberPW())
                .memberName(newMember.getMemberName())
                .memberPhone(newMember.getMemberPhone())
                .memberEmail(newMember.getMemberEmail())
                .build();

        Member saveMember =  memberRepository.save(memberEntity);

        return modelMapper.map(saveMember, MemberDTO.class);
    }

    public Optional<MemberDTO> findMember(String memberID){
        Optional<Member> member = memberRepository.findByMemberID(memberID);

        return Optional.ofNullable(modelMapper.map(member, MemberDTO.class));
    }

    public String findMemberID(String findMemberEmail) {
        Member findMember = memberRepository.findByMemberEmail(findMemberEmail);

        return findMember.getMemberID();
    }

    public void findMemberPW(MemberDTO memberInfo) {
        SimpleMailMessage message = new SimpleMailMessage();

        String authCode = createAuthCode();

        Optional<MemberDTO> member = findMember(memberInfo.getMemberID());
        if(!member.get().getMemberID().isEmpty()) {
            if(member.get().getMemberEmail().equals(memberInfo.getMemberEmail())) {
                message.setTo(memberInfo.getMemberEmail());
                message.setSubject("example 인증 코드");
                message.setText("인증 번호는 " + authCode + "입니다.");
                mailSender.send(message);
            }else {
                System.out.println("일치하지 않습니다.");
            }
        }else {
            System.out.println("일치하지 않습니다.");
        }
    }

    private String createAuthCode() {
        int min = 100000; // 최소값: 100000 (6자리)
        int max = 999999; // 최대값: 999999 (6자리)

        int code = (int) (Math.random() * (max - min + 1)) + min;

        return String.valueOf(code);
    }
}
