package org.example.crm.dao;

import org.example.base.BaseMapper;
import org.example.crm.vo.User;

public interface UserMapper extends BaseMapper<User,Integer> {


    User queryUserByUserName(String userName);
}