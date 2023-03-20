package com.lmx.common.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity implements Serializable {
    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String userName;

    private Long phone;

}
