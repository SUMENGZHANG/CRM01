package org.example.crm.controller;

import org.example.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("cusPlan")
public class CusDevPlanController extends BaseController {
    @RequestMapping("index")
    public String index(){
        return "cusPlan";
    }

}
