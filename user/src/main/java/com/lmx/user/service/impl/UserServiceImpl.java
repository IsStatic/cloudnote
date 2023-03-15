package com.lmx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmx.common.entitys.RespBean;
import com.lmx.user.entity.User;
import com.lmx.user.mapper.UserMapper;
import com.lmx.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean login(String phone, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        return RespBean.success(user);
    }
}
