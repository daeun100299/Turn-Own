package com.pde.turnown.user.service;

import com.pde.turnown.user.entity.User;
import com.pde.turnown.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String id){
        Optional<User> user = userRepository.findByUserId(id);

        /* 별도의 검증 로직 작성 */

        return user;
    }
}
