package com.lmx.api.configs;

import com.lmx.common.entitys.UserInfoEntity;

public class UserThreadLocal {
    private final static ThreadLocal<UserInfoEntity> userThreadLocal = new ThreadLocal<UserInfoEntity>();

    public static void setUser(UserInfoEntity userInfoEntity){
        userThreadLocal.set(userInfoEntity);
    }
    public static UserInfoEntity getUserInfo(){
        return userThreadLocal.get();
    }
}
