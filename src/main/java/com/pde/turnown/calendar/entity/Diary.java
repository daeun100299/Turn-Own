package com.pde.turnown.calendar.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tbl_diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DIARY_NUM")
    private int diaryNum;
    @Column(name="CALENDAR_CODE")
    private int calendarCode;
    @Column(name="MEMBER_ID")
    private String memberID;
    @Column(name="DIARY_TITLE")
    private int diaryTitle;
    @Column(name="DIARY_CONTENT")
    private String diaryContent;
    @CreationTimestamp
    @Column(name="DIARY_DATE")
    private Timestamp diaryDate;

    public Diary diaryTitle(int val) {
        this.diaryTitle = val;
        return this;
    }

    public Diary diaryContent(String val) {
        this.diaryContent = val;
        return this;
    }

    @Builder
    public Diary(int diaryNum, int calendarCode, String memberID, int diaryTitle, String diaryContent, Timestamp diaryDate) {
        this.diaryNum = diaryNum;
        this.calendarCode = calendarCode;
        this.memberID = memberID;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryDate = diaryDate;
    }
}
