package com.lmx.common.entitys;

/**
 * 公共返回对象枚举
 *
 * @author: LC
 * @date 2022/3/2 1:44 下午
 * @ClassName: RespBean
 */


public enum RespBeanEnum {

    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),

    MAPPER_ERROR(501,"使用mybatis异常"),

    //登录模块
    LOGIN_ERROR(400210, "用户名或者密码不正确"),
    LOGIN_NULL_ERROR(400201,"没有该用户"),
    MOBILE_ERROR(400211, "手机号码格式不正确"),
    BIND_ERROR(400212, "参数校验异常"),
    MOBILE_NOT_EXIST(400213, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(400214, "更新密码失败"),
    SESSION_ERROR(400215, "用户SESSION不存在"),

    //HDFS模块
    HDFS_CREATE_ERROR(500301,"创建文件失败,文件已存在"),
    HDFS_PATH_ERROR(500302,"目标路径为空"),
    HDFS_CREATE_NULL_ERROR(40001,"请求路径,文件名,内容不能为空"),
    HDFS_ERROR(500303,"未知异常-HDFS"),
    HDFS_FILENOTNULL_ERROR(40040,"文件不能为空"),

    USER_CREATE_ERROR(40001,"用户已存在"),
    USER_CREATE_NOTNULL_ERROR(40002,"手机号或密码为空"),
    USER_PASSWORD_NOTNULL_ERROR(40003,"密码不能为空"),
    USER_PHONE_NOTNULL_ERROR(40004,"密码不能为空"),
    USER_UPDATE_ERROR(40005,"更新失败"),
    USER_NOTLOGIN_ERROR(40006,"未登录"),
    USER_PHONE_ERROR(40007,"该手机已存在"),
    USER_PASSWORD_ERROR(40008,"密码错误"),

    NOTE_ISEXIST_ERROR(40020,"该笔记已存在，无法再次创建"),
    NOTE_FILETRME_ERROR(40021,"无法获取文件流"),
    NOTE_UPDATE_ERROR(40022,"参数不能为空"),
    NOTE_NOT_ERROR(40023,"笔记不存在"),
    ;



    ;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    RespBeanEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
