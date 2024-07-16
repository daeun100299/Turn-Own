package com.pde.turnown.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Member(String memberID, String memberPW, String memberName, String memberPhone, String memberEmail) {
        this.memberID = memberID;
        this.memberPW = memberPW;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
    }
}