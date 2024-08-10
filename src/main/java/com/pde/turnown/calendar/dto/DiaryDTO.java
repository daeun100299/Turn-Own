package com.pde.turnown.calendar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryDTO {
    private int diaryNum;
    private int calendarCode; // 일기, 투두
    private String memberID;
    private int diaryTitle; // 번호에 따른 표정 사진
    private String diaryContent;
    private LocalDateTime diaryDate;

    public DiaryDTO(int diaryNum, int diaryTitle, String diaryContent) {
        this.diaryNum = diaryNum;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
    }

    public DiaryDTO(int calendarCode, String memberID, int diaryTitle, String diaryContent) {
        this.calendarCode = calendarCode;
        this.memberID = memberID;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
    }
}
