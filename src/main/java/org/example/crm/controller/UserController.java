package org.example.crm.controller;

import org.example.base.BaseController;
import org.example.crm.Utils.LoginUserUtil;
import org.example.crm.exception.ParamsException;
import org.example.crm.model.ResultInfo;
import org.example.crm.model.UserModel;
import org.example.crm.service.UserService;
import org.example.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping("user/queryUserByUserId")
    @ResponseBody
    public User queryUserByUserId(Integer userId) {
        return userService.selectByPrimaryKey(userId);
    }

    @RequestMapping("user/login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd) {
        UserModel userModel = userService.login(userName, userPwd);
        return success("登陆成功", userModel);


    }

    @RequestMapping("user/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request), oldPassword, newPassword, confirmPassword);
        return success("更新成功");
    }
}
