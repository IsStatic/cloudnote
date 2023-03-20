package com.lmx.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.common.entitys.*;
import com.lmx.user.entity.User;



/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
public interface IUserService extends IService<User> {

    LoginResponse login(LoginEntity login);

    UserInfoEntity check(UserInfoRequest userInfoRequest);

    RespBean register(LoginEntity login);

    RespBean update(UpdateEntity update);

    RespBean updatePassWord(PassWordEntity passWord);
}
