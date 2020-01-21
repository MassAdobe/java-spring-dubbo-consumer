package com.massadobe.attempt.application.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.massadobe.attempt.application.service.UserInterface;
import com.massadobe.attempt.common.pojo.ResponseStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: Hello
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 14:50
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
@RequestMapping(value = "/hello")
public class Hello {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/read")
    @SentinelResource("read")
    public ResponseStruct Read() {
        return ResponseStruct.success(this.userInterface.listRead());
    }

    @GetMapping("/write")
    @SentinelResource("write")
    public ResponseStruct Write() {
        return ResponseStruct.success(this.userInterface.listWrite());
    }

    @GetMapping("/printSth")
    @SentinelResource("printSth")
    public ResponseStruct PrintSth() {
        return ResponseStruct.success("hello world!");
    }
}
