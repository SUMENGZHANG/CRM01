package org.example.crm;

import com.alibaba.fastjson.JSON;
import org.example.crm.exception.NoLoginException;
import org.example.crm.exception.ParamsException;
import org.example.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();
        if(ex instanceof NoLoginException){
            /**
             * 没登录
             */
            NoLoginException no = (NoLoginException) ex;
            mv.setViewName("no_login");
            mv.addObject("msg",no.getMsg());
            mv.addObject("ctx", httpServletRequest.getContextPath());
            return mv;

        }
        mv.setViewName("error");
        mv.addObject("code",400);
        mv.addObject("msg","系统裂开");
        /**
         * 返回值可以为视图也可以是json；
         *
         * json: 错误的json信息
         * 如果方法级别存在@ResponseBody  那返回的为json
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            ResponseBody responseBody = hm.getMethod().getDeclaredAnnotation(ResponseBody.class);
            if (responseBody == null) {
                /**
                 * 方法返回视图
                 */
                if(ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    mv.addObject(("code"),pe.getCode());
                    mv.addObject("msg",pe.getMsg());

                }
                return mv;
            } else {
                /**
                 * 方法返回json
                 */

                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统错误，请稍后再试");
                if (ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = null;
                try {
                    printWriter = httpServletResponse.getWriter();
                    printWriter.write(JSON.toJSONString(resultInfo));
                    printWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (printWriter != null) {
                        printWriter.close();
                    }
                }
                return null;
            }

        }
        return mv;
    }

}
