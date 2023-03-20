package com.lmx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.common.entitys.*;
import com.lmx.common.utils.MD5Utils;
import com.lmx.user.entity.User;
import com.lmx.user.mapper.UserMapper;
import com.lmx.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;


/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoginResponse login(LoginEntity login) {
        String phone = login.getMobile();
        String password = login.getPassword();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        LoginResponse loginResponse = new LoginResponse();
        if(null == user){
            loginResponse.setRespBean(RespBean.error(RespBeanEnum.LOGIN_NULL_ERROR));
            return loginResponse;
        }
        if (!MD5Utils.fromPassToDBpass(password,user.getSlat()).equals(user.getPassword())){
            loginResponse.setRespBean(RespBean.error(RespBeanEnum.LOGIN_ERROR));
            return loginResponse;
        }
        loginResponse.setRespBean(RespBean.success());
        loginResponse.setUserInfoEntity(setUserInfoByUser(new UserInfoEntity(),user));
        System.out.println(loginResponse.getUserInfoEntity().toString());
        return loginResponse;
    }

    @Override
    public UserInfoEntity check(UserInfoRequest userInfoRequest) {
        if (userInfoRequest == null) return null;
        if (StringUtils.isEmpty(userInfoRequest.getTicket())){
            User user = userMapper.selectById(userInfoRequest.getUserId());
            if (user == null){
                return null;
            }
            if (!MD5Utils.fromPassToDBpass(userInfoRequest.getPassword(),user.getSlat()).equals(user.getPassword())){
                return null;
            }
            return setUserInfoByUser(new UserInfoEntity(),user);
        }else {
            UserInfoEntity userInfoEntity = (UserInfoEntity) redisTemplate.opsForValue().get("user="+userInfoRequest.getTicket());
        }
        return null;
    }

    @Override
    public RespBean register(LoginEntity login) {
        if (login == null) return RespBean.error(RespBeanEnum.USER_CREATE_NOTNULL_ERROR);
        if (login.getMobile() == null) return RespBean.error(RespBeanEnum.USER_PHONE_NOTNULL_ERROR);
        if (login.getPassword() == null) return RespBean.error(RespBeanEnum.USER_PASSWORD_NOTNULL_ERROR);
        User user = userMapper.selectByPhone(login.getMobile());
        if (null == user){
            user = new User();
            Random random = new Random();
            int s = 0;
            for (int i = 0; i < 5; i++) {
                s*=10;
                s+= random.nextInt(9) %9 ;
            }
            String slat = String.valueOf(s);
            user.setPhone(Long.valueOf(login.getMobile()));
            user.setPassword(MD5Utils.fromPassToDBpass(login.getPassword(),slat));
            user.setSlat(slat);
            user.setUserName(login.getMobile());
            user.setCreatedAt(new Date());
            userMapper.insert(user);
        }
        return RespBean.error(RespBeanEnum.USER_CREATE_ERROR);
    }

    @Override
    public RespBean update(UpdateEntity update) {
        System.out.println("start-update");
        if (update == null) return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        if (update.getNewPhone() == null )return RespBean.error(RespBeanEnum.USER_PASSWORD_NOTNULL_ERROR);
        ValueOperations ops = redisTemplate.opsForValue();
        UserInfoEntity userInfoEntity = (UserInfoEntity)ops.get("user=" + update.getTicket());
        if (userInfoEntity ==null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);

        if (null != userMapper.selectOne(new QueryWrapper<User>().eq("phone",update.getNewPhone()))){
            return RespBean.error(RespBeanEnum.USER_PHONE_ERROR);
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("phone",userInfoEntity.getPhone());
        User user = new User();
        user.setUpdatedAt(new Date());
        user.setPhone(Long.valueOf(update.getNewPhone()));
        user.setUserName(update.getUserName());
        userMapper.update(user,updateWrapper);
        user = userMapper.selectByPhone(update.getNewPhone());
        if (user == null) return RespBean.error(RespBeanEnum.USER_CREATE_ERROR);
        redisTemplate.delete("user="+update.getTicket());
        ops.set("user="+update.getTicket(),setUserInfoByUser(new UserInfoEntity(),user));
        return RespBean.success();
    }

    @Override
    public RespBean updatePassWord(PassWordEntity passWord) {
        if (passWord == null || passWord.getTicket() == null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        if (passWord.getPassWord() == null || passWord.getNewPass() == null) return RespBean.error(RespBeanEnum.USER_PHONE_NOTNULL_ERROR);
        ValueOperations ops = redisTemplate.opsForValue();
        UserInfoEntity userInfoEntity = (UserInfoEntity) ops.get("user="+passWord.getTicket());
        if ( userInfoEntity== null) return RespBean.error(RespBeanEnum.USER_NOTLOGIN_ERROR);
        User user = userMapper.selectByPhone(String.valueOf(userInfoEntity.getPhone()));
        if (user ==null ) return RespBean.error(RespBeanEnum.LOGIN_NULL_ERROR);
        if (!MD5Utils.fromPassToDBpass(passWord.getPassWord(),user.getSlat()).equals(user.getPassword())) return RespBean.error(RespBeanEnum.USER_PASSWORD_ERROR);
        user.setPassword(MD5Utils.fromPassToDBpass(passWord.getNewPass(),user.getSlat()));
        user.setUpdatedAt(new Date());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("phone",user.getPhone());
        userMapper.update(user,updateWrapper);
        return RespBean.success();
    }

    public static UserInfoEntity setUserInfoByUser(UserInfoEntity userInfoEntity, User user){
        userInfoEntity.setUserName(user.getUserName());
        userInfoEntity.setCreatedAt(user.getCreatedAt());
        userInfoEntity.setDeletedAt(user.getDeletedAt());
        userInfoEntity.setId(user.getId());
        userInfoEntity.setPhone(user.getPhone());
        userInfoEntity.setPhone(user.getPhone());
        return userInfoEntity;
    }
}
