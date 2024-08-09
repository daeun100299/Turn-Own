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

    public String findMemberByEmail(String findMemberEmail) {
        Member findMember = memberRepository.findByMemberEmail(findMemberEmail);

        return findMember.getMemberID();
    }

    public void findMemberPW(Optional<MemberDTO> memberInfo) {
        MemberDTO memberInfoDTO = memberInfo.get();

        Member memberInfoEntity = Member.builder()
                .memberID(memberInfoDTO.getMemberID())
                .memberPW(memberInfoDTO.getMemberPW())
                .memberName(memberInfoDTO.getMemberName())
                .memberPhone(memberInfoDTO.getMemberPhone())
                .memberEmail(memberInfoDTO.getMemberEmail())
                .emailCode(memberInfoDTO.getEmailCode())
                .build();

        memberRepository.save(memberInfoEntity);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(memberInfoDTO.getMemberEmail());
        message.setSubject("Turn-Own 인증 코드");
        message.setText("인증 번호는 " + memberInfoDTO.getEmailCode() + "입니다.");

        mailSender.send(message);
    }
}
