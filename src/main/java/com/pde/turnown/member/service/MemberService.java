package com.pde.turnown.member.service;

import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.entity.Member;
import com.pde.turnown.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
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

        Optional<MemberDTO> memberDTO = Optional.ofNullable(modelMapper.map(member, MemberDTO.class));

        return memberDTO;
    }
}
