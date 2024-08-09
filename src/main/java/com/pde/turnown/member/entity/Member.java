package com.pde.turnown.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tbl_member")
public class Member {
    @Id
    @Column(name="MEMBER_ID")
    private String memberID;
    @Column(name="MEMBER_PW")
    private String memberPW;
    @Column(name="MEMBER_NAME")
    private String memberName;
    @Column(name="MEMBER_PHONE")
    private String memberPhone;
    @Column(name="MEMBER_EMAIL")
    private String memberEmail;
    @Column(name="EMAIL_CODE")
    private int emailCode;

    @Builder
    public Member(String memberID, String memberPW, String memberName, String memberPhone, String memberEmail, int emailCode) {
        this.memberID = memberID;
        this.memberPW = memberPW;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.emailCode = emailCode;
    }
}
