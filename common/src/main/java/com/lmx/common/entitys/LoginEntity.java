package com.lmx.common.entitys;

import com.lmx.common.validators.isPhone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginEntity {
    @isPhone
    @NotNull
    private String mobile;

    @NotNull(message = "密码不能为空")
    @Length(min = 32) //由于前端传来的数据已经进行一次MD5
    private String password;
}
