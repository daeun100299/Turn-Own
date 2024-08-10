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
public class ThingDTO {
    private int thingNum;
    private int calendarCode; // 일기, 투두
    private String memberID;
    private String thingContent;
    private LocalDateTime thingDate;
    private char thingState; // N/Y(완료)
}
