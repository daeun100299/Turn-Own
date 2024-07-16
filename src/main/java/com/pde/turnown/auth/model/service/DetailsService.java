package com.pde.turnown.auth.model.service;

import com.pde.turnown.auth.model.DetailsUser;
import com.pde.turnown.user.service.UserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsService implements UserDetailsService {
    private final UserService userService;

    public DetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 아이디 확인
        if (userId == null || userId.equals("")) {
            throw new AuthenticationServiceException("아이디가 존재하지 않습니다.");
        } else {
            return userService.loginUser(userId)
                    .map(data -> new DetailsUser(Optional.of(data)))
                    .orElseThrow(() -> new AuthenticationServiceException(userId));
        }
    }
}
