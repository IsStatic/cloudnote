package com.lmx.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
@Getter
@Setter
@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String userName;

    private String password;

    private Long phone;

    private String slat;

}
