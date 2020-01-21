package com.massadobe.attempt.application.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.massadobe.attempt.common.constants.ConstantsConfig;
import com.massadobe.attempt.common.constants.HttpConstant;
import com.massadobe.attempt.common.enums.ErrorCodeMsg;
import com.massadobe.attempt.application.exception.AttemptException;
import com.massadobe.attempt.common.pojo.RequestUser;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: SessionAspect
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 14:15
 * @Version: 1.0.0
 * @param: * @param null
 */
@Aspect
@Component
public class SessionAspect {
    private final static Logger logger = LoggerFactory.getLogger(SessionAspect.class);

    private static String token = null;
    private static String deltaTime = null;
    private static RequestUser requestUser = null;

    @Pointcut("execution(* com.massadobe.attempt.application.controller.*.*(..))")
    public void Session() {
    }

    @Before("Session()")
    public void SessionBefore() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (!Strings.isNullOrEmpty(request.getHeader(ConstantsConfig.DELTATIME))) {
            deltaTime = request.getHeader(ConstantsConfig.DELTATIME);
        }
//        try {
//            BufferedReader br = request.getReader();
//            String str, wholeStr = "";
//            while ((str = br.readLine()) != null) {
//                wholeStr += str;
//            }
//            System.out.println(wholeStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (!Strings.isNullOrEmpty(request.getHeader(HttpConstant.AUTHORIZATION))) {
            setToken(request.getHeader(HttpConstant.AUTHORIZATION));
        }
        if (!Strings.isNullOrEmpty(request.getHeader(ConstantsConfig.USER_INFO))) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                setRequestUser(objectMapper.readValue(request.getHeader(ConstantsConfig.USER_INFO), RequestUser.class));
            } catch (IOException e) {
                logger.error("JSON解析错误：{}", e);
                throw new AttemptException(ErrorCodeMsg.JSON_DECODE_ERROR);
            }
        }
    }

    @After("Session()")
    public void SessionAfter() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        if (!Strings.isNullOrEmpty(token)) {
            response.setHeader(HttpConstant.AUTHORIZATION, getToken());
        }
        if (!Strings.isNullOrEmpty(deltaTime)) {
            response.setHeader(ConstantsConfig.DELTATIME, deltaTime);
        }
        response.setHeader(HttpConstant.CONTENT_TYPE, HttpConstant.CONTENT_TYPE_INNER);
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SessionAspect.token = token;
    }

    public static RequestUser getRequestUser() {
        return requestUser;
    }

    public static void setRequestUser(RequestUser requestUser) {
        SessionAspect.requestUser = requestUser;
    }
}
