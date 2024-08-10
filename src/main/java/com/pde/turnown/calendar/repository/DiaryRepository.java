package com.pde.turnown.calendar.repository;

import com.pde.turnown.calendar.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    Diary findByDiaryNum(int diaryNum);
}
