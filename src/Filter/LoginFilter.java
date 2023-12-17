package Filter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class LoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogFilter initialized");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("LogFilter`s doFilter start");

        HttpSession session = request.getSession(false);

        // 检查 session 中是否包含登录属性
        if (session != null && session.getAttribute("login") != null) {
            // 用户已登录，继续执行过滤器链
            chain.doFilter(request, response);
        } else {
            // 用户未登录，检查当前请求是否已经是登录页面
            String loginPage = request.getContextPath() + "/login.html";
            String requestURI = request.getRequestURI();

            if (requestURI.equals(loginPage)) {
                // 如果当前请求已经是登录页面，继续执行过滤器链
                chain.doFilter(request, response);
            } else {
                // 用户未登录，跳转到登录页面
                System.out.println("User is not logged in. Redirecting to login page.");
                response.sendRedirect(loginPage);
            }
        }

        System.out.println("LogFilter`s doFilter end");
    }


    @Override
    public void destroy() {
        System.out.println("LogFilter destroyed");
    }
}
