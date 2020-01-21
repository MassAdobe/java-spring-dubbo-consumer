package com.massadobe.attempt.application.service;

import com.massadobe.attempt.application.entity.RebuildUsers;

import java.util.List;

/**
 * @ClassName: UserInterface
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 14:41
 * @Version: 1.0.0
 * @param: * @param null
 */
public interface UserInterface {

    List<RebuildUsers> listRead();

    List<RebuildUsers> listWrite();
}
