package com.pde.turnown.user;

import com.pde.turnown.user.dto.UserDTO;
import com.pde.turnown.user.entity.User;
import com.pde.turnown.user.service.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.pde.turnown.common.UserRole.ALL;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private static Stream<Arguments> registUser() {
        return Stream.of(
                Arguments.of(
                        new UserDTO(
                                "id01"
                                , "pass01"
                                , "박다은"
                                , "goode439767@gamil.com"
                                ,ALL
                                , 'Y'
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("registUser")
    void registTestUser(UserDTO userDto) {
         User newUser = userService.registUser(userDto);

        if(newUser.getUserNo() > 0) {
            System.out.println("사용자 등록을 성공했습니다.");
        }else {
            System.out.println("사용자 등록에 실패했습니다.");
        }
    }
}
