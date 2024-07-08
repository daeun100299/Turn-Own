package com.pde.turnown.user.controller;

import com.pde.turnown.user.entity.User;
import com.pde.turnown.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        user.setUserState('Y');

        User value = userRepository.save(user);

        if (Objects.isNull(value)) {
            return "회원 가입 실패";
        } else {
            return "회원 가입 성공!";
        }
    }
}
