package com.pde.turnown.member;

import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
                            "id01"
                            , "pass01"
                            , "김철수"
                            , "010-1111-1111"
                            , "user01@pde.com"
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
}
