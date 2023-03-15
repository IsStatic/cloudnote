package com.lmx.api.entitys;

import lombok.Data;

@Data
public class LoginEntity {

//    @NotNull
//    @isPhone
    private String mobile;

//    @NotNull(message = "密码不能为空")
//    @Length(min = 32)
    private String password;
}
