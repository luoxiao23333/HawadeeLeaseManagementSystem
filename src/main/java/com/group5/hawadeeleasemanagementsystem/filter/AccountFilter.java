package com.group5.hawadeeleasemanagementsystem.filter;


import com.group5.hawadeeleasemanagementsystem.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * 账户拦截器，如果用户登陆超过server.servlet.session.timeout,则登陆超时，退回到登陆界面
 */
@WebFilter(urlPatterns = {"/*"})
public class AccountFilter implements Filter {

    private boolean isBeforeLogin(String path){
        return path.equals("") || path.startsWith("/account/");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        //保留"/"后面
        String path = request.getRequestURI().substring(
                request.getContextPath().length()).replaceAll("[/]+$", "");

        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn || isBeforeLogin(path)) {
            chain.doFilter(req, res);
        }
        else {
            response.sendRedirect("/account/login");
        }
    }
}
