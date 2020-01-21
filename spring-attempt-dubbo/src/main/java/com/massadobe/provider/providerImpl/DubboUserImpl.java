package com.massadobe.provider.providerImpl;

import com.massadobe.attempt.application.dao.RebuildUsersMapper;
import com.massadobe.attempt.application.entity.RebuildUsers;
import com.massadobe.attempt.common.annotation.Read;
import com.massadobe.attempt.common.annotation.Write;
import com.massadobe.provider.DubboUserInterface;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: DubboUserImpl
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-16 17:43
 * @Version: 1.0.0
 * @param: * @param null
 */
@Service
public class DubboUserImpl implements DubboUserInterface {

    @Autowired
    private RebuildUsersMapper rebuildUsersMapper;

    @Override
    @Read
    public List<RebuildUsers> dubboListRead() {
        return this.rebuildUsersMapper.selectAll();
    }

    @Override
    @Write
    public List<RebuildUsers> dubboListWrite() {
        return this.rebuildUsersMapper.selectAll();
    }
}
