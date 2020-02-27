package org.example.crm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.base.BaseService;
import org.example.crm.query.SaleChanceQuery;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery) {
        Map<String, Object> result = new HashMap<String, Object>();
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getRows());
        PageInfo<SaleChance> pageInfo = new PageInfo<>(selectByParams(saleChanceQuery));
        result.put("total",pageInfo.getTotal());
        result.put("rows",pageInfo.getList());
        return result;
    }
}
