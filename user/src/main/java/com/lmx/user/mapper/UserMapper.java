package com.lmx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmx.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *  Mapper 接口
 *
 * @author liang
 * @date 2023-03-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id = #{id}")
    public User selectByUserId2(int id);

    @Select("select * from user where id = #{id}")
    public User selectByUserId(String id);

    @Select("Select * from user where phone = #{phone}")
    public User selectByPhone(String phone);
}
