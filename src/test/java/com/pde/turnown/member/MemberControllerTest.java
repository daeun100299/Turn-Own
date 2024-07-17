package com.pde.turnown.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pde.turnown.member.controller.MemberController;
import com.pde.turnown.member.dto.MemberDTO;
import com.pde.turnown.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 테스트")
    public void signMemberTest() throws Exception {
        MemberDTO signupMember = new MemberDTO("id02", "pass02", "이영수", "010-2222-2222", "lee@pde.com");

        doNothing().when(memberService).signupMember(any());

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(signupMember))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.memberID").value("id02"))
                .andExpect(jsonPath("$.memberPW").value("pass02"))
                .andExpect(jsonPath("$.memberName").value("이영수"))
                .andExpect(jsonPath("$.memberPhone").value("010-2222-2222"))
                .andExpect(jsonPath("$.memberEmail").value("lee@pde.com"))
                .andDo(print());
    }
}
