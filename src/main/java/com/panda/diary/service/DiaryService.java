package com.panda.diary.service;

import com.panda.diary.model.DiaryModel;
import com.panda.diary.repository.DiaryRepo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DiaryService {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    final DiaryRepo diaryRepo;
    final SessionService sessionService;

    public DiaryService(DiaryRepo diaryRepo, SessionService sessionService) {
        this.diaryRepo = diaryRepo;
        this.sessionService = sessionService;
    }

    public String saveDiary(DiaryModel diaryModel) {
        Date date = new Date();
        String nowDate = dateFormat.format(date);
        diaryModel.setDate(nowDate);
        diaryModel.setUserId(sessionService.findActiveUserByTitle());
        diaryRepo.save(diaryModel);
        return "Save Successfully.!";
    }

    public List<DiaryModel> getAllByUserId() {
        return diaryRepo.findAllByUserId(sessionService.findActiveUserByTitle());
    }
}
