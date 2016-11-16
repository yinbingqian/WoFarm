package com.lnpdit.woofarm.mservice;

import com.lnpdit.woofarm.base.framework.BaseService;
import com.lnpdit.woofarm.entity.UserEntity;
import com.lnpdit.woofarm.entity.http.request.RegisterEntity;

public interface UserService extends BaseService {
    
    void userRegister(RegisterEntity registerEntity);

    void userLogin(String username, String password);

    void getUserInfo(String userid);

    void getContacts(String userid);
    
    void updateUserInfo(UserEntity userEntity);
    
    void test(String cityCode);

}
