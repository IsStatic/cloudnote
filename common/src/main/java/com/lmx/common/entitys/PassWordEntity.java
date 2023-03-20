package com.lmx.common.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassWordEntity implements Serializable {
    private String ticket;
    private String passWord;
    private String newPass;
}
