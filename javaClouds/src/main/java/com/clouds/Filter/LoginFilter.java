package com.clouds.Filter;

import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (uri.contains("/register.jsp") || uri.contains("/vcode")
                || uri.contains("/css/") || uri.contains("/js/")
                || uri.contains("/fonts/") || uri.contains("/images/")
                || uri.contains("/loginServlet") || uri.contains("/registerServlet")
                || uri.contains("/registerFindUserServlet")) {
            chain.doFilter(req, resp);
        } else {
            Object user = request.getSession().getAttribute("user");
            if (!ObjectUtils.isEmpty(user)) {
                chain.doFilter(req, resp);
            } else {
                HttpServletResponse response = (HttpServletResponse) resp;
                request.getServletContext().setAttribute("msg", "尚未登录，请登录");
                response.sendRedirect(request.getContextPath() + "/register.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
