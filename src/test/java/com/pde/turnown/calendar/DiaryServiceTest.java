package com.pde.turnown.calendar;

import com.pde.turnown.calendar.dto.DiaryDTO;
import com.pde.turnown.calendar.service.DiaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class DiaryServiceTest {
    @Autowired
    private DiaryService diaryService;

    private static Stream<Arguments> writeDiary() {
        return Stream.of(
                Arguments.of(
                        new DiaryDTO(
                                1,
                                "id01",
                                1,
                                "일기 내용1"
                        )
                )
        );
    }

    private static Stream<Arguments> updateDiary() {
        return Stream.of(
                Arguments.of(
                        new DiaryDTO(
                                1,
                                2,
                                "수정"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("writeDiary")
    void insertDiaryTest(DiaryDTO newDiary) {
        Assertions.assertDoesNotThrow(
                () -> {
                    diaryService.insertDiary(newDiary);
                }
        );
    }

    @ParameterizedTest
    @MethodSource("updateDiary")
    void modifyDiaryTest(DiaryDTO updateDiary) {
        Assertions.assertDoesNotThrow(
                () -> {
                    diaryService.modifyDiary(updateDiary);
                }
        );
    }
}
