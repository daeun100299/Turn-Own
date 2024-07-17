package com.pde.turnown.auth.handler;

import com.pde.turnown.auth.model.DetailsMember;
import com.pde.turnown.auth.model.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* 실제 인증 진행 - 비밀번호 매칭해서 실제 인증 진행 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private DetailsService detailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken loginToken = (UsernamePasswordAuthenticationToken) authentication;

        String memberID = loginToken.getName();
        String memberPW = (String) loginToken.getCredentials();

        DetailsMember detailsMember = (DetailsMember) detailsService.loadUserByUsername(memberID);

        if(!passwordEncoder.matches(memberPW, detailsMember.getPassword())) {
            throw new BadCredentialsException(memberPW + "는 틀린 비밀번호입니다.");
        }

        // 토큰 생성
        return new UsernamePasswordAuthenticationToken(detailsMember, memberPW, detailsMember.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
