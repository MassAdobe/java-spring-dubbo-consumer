package com.massadobe.attempt.common.enums;

/**
 * @ClassName: ErrorCodeMsg
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:28
 * @Version: 1.0.0
 * @param: * @param null
 */
public enum ErrorCodeMsg {
    // 系统级别 9000-->9999
    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(9999, "未知错误"),
    SERVER_ERROR(9998, "服务错误"),
    PARAM_ERROR(9997, "参数错误"),
    PAGE_OR_API_ERROR(9996, "页面或接口错误"),
    // 业务错误 8000-->8999
    NOT_LOGIN(8999, "未登陆"),
    UN_LAWFUL(8998, "非法"),
    NOT_FOUND_USER(8997, "该用户不存在"),
    USER_PASSWORD_ERROR(8996, "用户名或密码错误"),
    TOKEN_EMPTY(8995, "Token为空"),
    ENCODE_INSTANCE_ERROR(8994, "encode-getInstance()方法异常"),
    BASE64_ENCODE_ERROR(8993, "base64-加密异常"),
    CIPHER_ERROR(8992, "初始化Cipher对象异常"),
    SECERT_KEY_ERROR(8991, "加密异常，密钥有误"),
    GENE_TOKEN_FAILURE_ERROR(8990, "Token生成失败"),
    TOKEN_OUT_TIME_ERROR(8989, "Token过期"),
    TOKEN_UNSUPPORT_ENCODE_ERROR(8988, "JWTToken认证解密出现UnsupportedEncodingException异常"),
    TOKEN_DECODE_ERROR(8987, "解密Token中的公共信息出现JWTDecodeException异常"),
    JSON_DECODE_ERROR(8986, "JSON解析错误"),

    // 数据级别
    ;

    private int code;
    private String message;

    ErrorCodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
