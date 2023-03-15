package com.lmx.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmx.common.entitys.RespBean;
import com.lmx.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
public interface IUserService extends IService<User> {

    RespBean login(String phone, String password, HttpServletRequest request, HttpServletResponse response);
}
