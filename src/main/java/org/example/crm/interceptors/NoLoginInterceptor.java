package org.example.crm.interceptors;

import org.example.crm.Utils.LoginUserUtil;
import org.example.crm.exception.NoLoginException;
import org.example.crm.service.UserService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         *  get cookie and parse the userId
         *  if user exists, release or interceptor
         */
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
//        if(userId==0||null==userService.selectByPrimaryKey(userId)){
//            response.sendRedirect(request.getContextPath()+"/index");
//            return false;
//        }
        if(userId==0||null==userService.selectByPrimaryKey(userId)){
            throw new NoLoginException();
        }

        return true;
    }
}
