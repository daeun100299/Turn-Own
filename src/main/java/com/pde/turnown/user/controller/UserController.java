package com.pde.turnown.user.controller;

import com.pde.turnown.common.ResponseDTO;
import com.pde.turnown.user.entity.User;
import com.pde.turnown.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/signup", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResponseDTO> signup(@RequestBody User user) {
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        user.setUserState('Y');

        User newUser = userRepository.save(user);

        if(newUser.getUserNo() != 0) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "새로운 사용자 등록에 성공했습니다.", newUser));
        }else {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.BAD_REQUEST, "새로운 사용자 등록에 실패했습니다.", null));
        }
    }
}
