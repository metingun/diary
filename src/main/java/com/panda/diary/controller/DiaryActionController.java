package com.panda.diary.controller;

import com.panda.diary.model.DiaryModel;
import com.panda.diary.model.ResponseModel;
import com.panda.diary.service.DiaryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restful/diary", produces = "application/json")
public class DiaryActionController {

    private final DiaryService diaryService;

    public DiaryActionController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseModel saveDiary(@RequestBody DiaryModel diaryModel) {
        try {
            return ResponseModel
                    .createSuccessResponseWithData(diaryService.saveDiary(diaryModel),false);
        } catch (Exception e) {
            return ResponseModel.createErrorResponseWithErrorMessage(e);
        }
    }

    @RequestMapping(value = "/getAllByUserId", method = RequestMethod.POST)
    public ResponseModel getAllByUserId() {
        try {
            return ResponseModel
                    .createSuccessResponseWithData(diaryService.getAllByUserId(),false);
        } catch (Exception e) {
            return ResponseModel.createErrorResponseWithErrorMessage(e);
        }
    }
}
