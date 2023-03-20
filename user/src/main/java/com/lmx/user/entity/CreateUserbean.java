package com.lmx.user.entity;

import com.lmx.common.entitys.CreateUserRequest;

import javax.validation.constraints.NotNull;


public class CreateUserbean extends CreateUserRequest {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
