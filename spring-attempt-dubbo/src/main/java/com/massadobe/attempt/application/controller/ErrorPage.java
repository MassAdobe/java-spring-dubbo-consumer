package com.massadobe.attempt.application.controller;

import com.massadobe.attempt.common.constants.CmnConstants;
import com.massadobe.attempt.common.enums.ErrorCodeMsg;
import com.massadobe.attempt.common.pojo.ResponseStruct;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ErrorPage
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: 页面错误处理
 * @Date: Created in 2019-12-19 17:41
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
public class ErrorPage implements ErrorController {

    @RequestMapping("/error")
    public ResponseStruct ReturnErrorInfo() {
        return ResponseStruct.failure(ErrorCodeMsg.PAGE_OR_API_ERROR.getCode(), ErrorCodeMsg.PAGE_OR_API_ERROR.getMessage(), CmnConstants.EMPTY);
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
