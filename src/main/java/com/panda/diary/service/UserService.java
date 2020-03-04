package com.panda.diary.service;

import com.panda.diary.model.ChangePasswordModel;
import com.panda.diary.model.UserInfoModel;
import com.panda.diary.repository.UserInfoRepo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final
    UserInfoRepo userInfoRepo;
    private final ReadPropertyService readPropertyService;

    public UserService(UserInfoRepo userInfoRepo, ReadPropertyService readPropertyService) {
        this.userInfoRepo = userInfoRepo;
        this.readPropertyService = readPropertyService;
    }

    public String createUser(UserInfoModel userInfoModel, List<UserInfoModel> usersList) {
        if (usersList != null && usersList.size() != 0) {
            for (UserInfoModel userInfo : usersList) {
                if (userInfoModel.getUsername().equals(userInfo.getUsername())) {
                    return readPropertyService.getPropertyValue("1004");
                }
            }
        }
        userInfoRepo.save(userInfoModel);
        return readPropertyService.getPropertyValue("1003");
    }

    public List<UserInfoModel> getUsers() {
        return userInfoRepo.findAll();
    }

    public String changePassword(ChangePasswordModel changePassword, List<UserInfoModel> usersList) {
        for (UserInfoModel userInfoModel : usersList) {
            if (changePassword.getUsername().equals(userInfoModel.getUsername())
                && changePassword.getOldPassword().equals(userInfoModel.getPassword())) {
                userInfoModel.setPassword(changePassword.getNewPassword());
                updateDatabaseRow(userInfoModel);
                return readPropertyService.getPropertyValue("1001");
            }
        }
        return readPropertyService.getPropertyValue("1002");
    }

    public void updateDatabaseRow(UserInfoModel userInfoModel){
        UserInfoModel userInfo = userInfoRepo.getOne(userInfoModel.getId());
        userInfo.setPassword(userInfo.getPassword());
        userInfoRepo.save(userInfo);
    }
}
