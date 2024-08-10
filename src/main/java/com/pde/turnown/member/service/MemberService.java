package com.pde.turnown.member.service;

import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.entity.Member;
import com.pde.turnown.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
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

    public MemberDTO findMemberByEmail(String findMemberEmail) {
        Member findMember = memberRepository.findByMemberEmail(findMemberEmail);

        return modelMapper.map(findMember, MemberDTO.class);
    }

    public void findMemberPW(MemberDTO memberInfo) {
        Member memberInfoEntity = Member.builder()
                .memberID(memberInfo.getMemberID())
                .memberPW(memberInfo.getMemberPW())
                .memberName(memberInfo.getMemberName())
                .memberPhone(memberInfo.getMemberPhone())
                .memberEmail(memberInfo.getMemberEmail())
                .emailCode(memberInfo.getEmailCode())
                .build();

        memberRepository.save(memberInfoEntity);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(memberInfo.getMemberEmail());
        message.setSubject("Turn-Own 인증 코드");
        message.setText("인증 번호는 " + memberInfo.getEmailCode() + "입니다.");

        mailSender.send(message);
    }
}
