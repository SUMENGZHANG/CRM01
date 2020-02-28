package org.example.crm.dao;

import org.example.crm.vo.CusPlan;

public interface CusPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CusPlan record);

    int insertSelective(CusPlan record);

    CusPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CusPlan record);

    int updateByPrimaryKey(CusPlan record);
}