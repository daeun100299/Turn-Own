package com.pde.turnown.calendar.service;

import com.pde.turnown.calendar.dto.DiaryDTO;
import com.pde.turnown.calendar.entity.Diary;
import com.pde.turnown.calendar.repository.DiaryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final ModelMapper modelMapper;

    public DiaryService(DiaryRepository diaryRepository, ModelMapper modelMapper) {
        this.diaryRepository = diaryRepository;
        this.modelMapper = modelMapper;
    }

    public DiaryDTO insertDiary(DiaryDTO newDiary) {
        Diary diaryEntity = Diary.builder()
                .calendarCode(newDiary.getCalendarCode())
                .memberID(newDiary.getMemberID())
                .diaryTitle(newDiary.getDiaryTitle())
                .diaryContent(newDiary.getDiaryContent())
                .build();

        Diary saveDiary = diaryRepository.save(diaryEntity);

        return modelMapper.map(saveDiary, DiaryDTO.class);
    }

    public DiaryDTO modifyDiary(DiaryDTO updateDiary) {
        Diary findDiary = diaryRepository.findByDiaryNum(updateDiary.getDiaryNum());

        findDiary.diaryTitle(updateDiary.getDiaryTitle());
        findDiary.diaryContent(updateDiary.getDiaryContent());

        Diary saveDiary = diaryRepository.save(findDiary);

        return modelMapper.map(saveDiary, DiaryDTO.class);
    }
}
