package com.panda.diary.service;

import com.panda.diary.model.BirthdaySpecialModel;
import com.panda.diary.model.LoginModel;
import com.panda.diary.model.UserInfoModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final SessionService sessionService;

    public LoginService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public BirthdaySpecialModel loginCheck(LoginModel loginModel, List<UserInfoModel> usersList) {
        Date date = new Date();
        BirthdaySpecialModel birthdaySpecialModel=new BirthdaySpecialModel();
        String nowDate = dateFormat.format(date);
        for (UserInfoModel userInfoModel : usersList) {
            if (loginModel.getUsername().equals(userInfoModel.getUsername())
                && loginModel.getPassword().equals(userInfoModel.getPassword())) {
                if (userInfoModel.getBirthDate().equals(nowDate)){
                    birthdaySpecialModel.setBirthdayMessage("Happy Birthday "+userInfoModel.getName()+". I hope you always write good memories here.");
                }
                else {
                    birthdaySpecialModel.setBirthdayMessage(null);
                }
                birthdaySpecialModel.setUserInfoModel(userInfoModel);
                sessionService.updateSession((int) userInfoModel.getId());
                return birthdaySpecialModel;
            }
        }
        return null;
    }
}
