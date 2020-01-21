package com.massadobe.attempt.application.exception;

import com.massadobe.attempt.common.enums.ErrorCodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: AttemptException
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:28
 * @Version: 1.0.0
 * @param: * @param null
 */
public class AttemptException extends RuntimeException {
    private ErrorCodeMsg errorCodeMsg;
    private int code;
    private String message;

    public AttemptException(ErrorCodeMsg errorCodeMsg) {
        this.errorCodeMsg = errorCodeMsg;
        this.code = errorCodeMsg.getCode();
        this.message = errorCodeMsg.getMessage();
    }

    public ErrorCodeMsg getErrorCodeMsg() {
        return errorCodeMsg;
    }

}
