package com.pde.turnown.member;

import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    private static Stream<Arguments> getMember() {
        return Stream.of(
                Arguments.of(
                        new MemberDTO(
                            "id02"
                            , "pass02"
                            , "박영수"
                            , "010-2222-2222"
                            , "example@gmail.com"
                        )
                )
        );
    }

    private static Stream<Arguments> getMemberInfo() {
        return Stream.of(
                Arguments.of(
                        new MemberDTO(
                                "id02"
                                , "example@gmail.com"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getMember")
    void signupMemberTest(MemberDTO newMember) {
        Assertions.assertDoesNotThrow(
                () -> {
                    memberService.signupMember(newMember);
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = "user01@pde.com")
    void findMemberID(String findMemberEmail) {
        String findID =  memberService.findMemberID(findMemberEmail);
        Assertions.assertNotNull(findID, "아이디가 존재합니다.");
    }

    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void findMemberPW(MemberDTO memberInfo) {
        Assertions.assertDoesNotThrow(
                () -> {
                    memberService.findMemberPW(memberInfo);
                }
        );
    }
}
