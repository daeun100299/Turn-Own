package com.pde.turnown.user.service;

import com.pde.turnown.user.dto.UserDTO;
import com.pde.turnown.user.entity.User;
import com.pde.turnown.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registUser(UserDTO userDto) {
        User newUser = User.builder()
                .userId(userDto.getUserId())
                .userPass(userDto.getUserPass())
                .userName(userDto.getUserName())
                .userEmail(userDto.getUserEmail())
                .userState(userDto.getUserState())
                .build();

        System.out.println(newUser);

        return userRepository.save(newUser);
    }

    public Optional<User> loginUser(String userId){
        Optional<User> user = userRepository.findByUserId(userId);

        return user;
    }
}
