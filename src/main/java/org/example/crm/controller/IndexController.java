package org.example.crm.controller;

import org.example.crm.Utils.LoginUserUtil;
import org.example.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Resource
   private UserService userService;
    /**
     *  loginpage
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request){

        request.setAttribute("ctx",request.getContextPath());
        return "index";
    }

    /**
     * Backend homepage
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){

        request.setAttribute("ctx",request.getContextPath());
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        request.setAttribute("user",userService.selectByPrimaryKey(userId));

        return "main";
    }
}
