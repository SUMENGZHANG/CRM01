package org.example.crm.controller;

import org.example.base.BaseController;
import org.example.crm.Utils.LoginUserUtil;
import org.example.crm.model.ResultInfo;
import org.example.crm.query.SaleChanceQuery;
import org.example.crm.service.SaleChanceService;
import org.example.crm.service.UserService;
import org.example.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;



    @Resource
    private UserService userService;

    @RequestMapping("index")
    public String index(){

        return "sale_chance";
    }



    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){
        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }


    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveSaleChance(HttpServletRequest request,SaleChance saleChance){
        saleChance.setCreateMan(userService.selectByPrimaryKey(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
        saleChanceService.saveSaleChance(saleChance);
        return success("保存成功");
    }




    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer []ids){
        saleChanceService.deleteBatch(ids);
        return success("数据删除成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateScaleChance(SaleChance saleChance){
        saleChanceService.updateSaleChance(saleChance);
        return success("数据更新成功");

    }

}
