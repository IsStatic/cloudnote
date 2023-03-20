package com.lmx.common.entitys;

import com.lmx.common.validators.isPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEntity implements Serializable {
    private String ticket;
    @isPhone
    private String newPhone;
    private String userName;
}
