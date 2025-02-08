package com.vmeknowledge.interceptor;

import com.vmeknowledge.common.Result;
import com.vmeknowledge.threadLocal.UserThreadLocal;
import com.vmeknowledge.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法之前运行，返回true放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();//请求的url
        log.info("请求的url ： {}",url);

        if (url.contains("login")){//登录操作，放行
            log.info("登录操作，放行。。。");
            return true;
        }
        if (url.contains("register")){//注册操作，放行
            log.info("注册操作，放行。。。");
            return true;
        }

        String jwt = request.getHeader("token");

        if (!org.springframework.util.StringUtils.hasLength(jwt)){
            log.info("请求的token头为空");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = com.alibaba.fastjson.JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Integer id = Integer.valueOf(claims.get("id").toString());
            UserThreadLocal.setCurrentId(id);
            log.info("当前用户id为：{}",UserThreadLocal.getCurrentId());
            log.info("解析令牌成功");
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = com.alibaba.fastjson.JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        log.info("放行");
        return true;
    }


    @Override//放行之后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override//最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");;
    }
}
