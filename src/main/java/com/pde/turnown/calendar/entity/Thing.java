package com.pde.turnown.calendar.entity;

import jakarta.persistence.*;
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
@Table(name = "tbl_thing")
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="THING_NUM")
    private int thingNum;
    @Column(name="CALENDAR_CODE")
    private int calendarCode; // 일기, 투두
    @Column(name="MEMBER_ID")
    private String memberID;
    @Column(name="THING_CONTENT")
    private String thingContent;
    @CreationTimestamp
    @Column(name="THING_DATE")
    private Timestamp thingDate;
    @Column(name="THING_STATE")
    private char thingState; // N/Y(완료)
}
