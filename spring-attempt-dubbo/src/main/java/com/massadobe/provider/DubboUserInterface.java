package com.massadobe.provider;


import com.massadobe.attempt.application.entity.RebuildUsers;

import java.util.List;

/**
 * @ClassName: DubboUserInterface
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-16 17:43
 * @Version: 1.0.0
 * @param: * @param null
 */
public interface DubboUserInterface {

    List<RebuildUsers> dubboListRead();

    List<RebuildUsers> dubboListWrite();
}
