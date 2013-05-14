package com.chinatelecom.lottery.web.inteceptor;

import com.chinatelecom.lottery.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/15/13
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityInterceptor implements HandlerInterceptor {
    private List<String> excludedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }

        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser == null) {
            throw new AuthorizationException();

        }
        if (requestUri.toLowerCase().contains("admin") && !((User) currentUser).getUserName().equalsIgnoreCase("sa")) {
            throw new AuthorizationException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
