package com.lmx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmx.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author liang
 * @date 2023-03-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
