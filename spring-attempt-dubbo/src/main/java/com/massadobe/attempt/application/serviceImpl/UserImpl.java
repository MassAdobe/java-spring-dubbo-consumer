package com.massadobe.attempt.application.serviceImpl;

import com.massadobe.attempt.application.dao.RebuildUsersMapper;
import com.massadobe.attempt.application.entity.RebuildUsers;
import com.massadobe.attempt.application.service.UserInterface;
import com.massadobe.attempt.common.annotation.Read;
import com.massadobe.attempt.common.annotation.Write;
import com.massadobe.attempt.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserImpl implements UserInterface {

    @Autowired
    private RebuildUsersMapper rebuildUsersMapper;

    @Override
    @Read
    public List<RebuildUsers> listRead() {
        this.rebuildUsersMapper.insert(new RebuildUsers(null, CommonUtils.getRandomString(8), "TESTING", "13800138001", CommonUtils.getRandomString(18), "SALT", Short.parseShort("1"), Short.parseShort("1"), new Date(), new Date()));
        return this.rebuildUsersMapper.selectAll();
    }

    @Override
    @Write
    public List<RebuildUsers> listWrite() {
        this.rebuildUsersMapper.insert(new RebuildUsers(null, CommonUtils.getRandomString(8), "GNITSET", "13800138002", CommonUtils.getRandomString(18), "SALT", Short.parseShort("1"), Short.parseShort("1"), new Date(), new Date()));
        return this.rebuildUsersMapper.selectAll();
    }
}
