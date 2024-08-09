package com.pde.turnown.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private String memberID;
    private String memberPW;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private int emailCode;

    public MemberDTO(String memberID, String memberEmail) {
        this.memberID = memberID;
        this.memberEmail = memberEmail;
    }

    public MemberDTO(String memberID, String memberPW, String memberName, String memberPhone, String memberEmail) {
        this.memberID = memberID;
        this.memberPW = memberPW;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
    }
}
