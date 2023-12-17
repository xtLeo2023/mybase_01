package Filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class FontFilter extends HttpFilter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FontFilter initialized");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FontFilter`s doFilter start");

        // 设置请求编码
        request.setCharacterEncoding("utf-8");

        // 设置响应编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 继续执行过滤器链
        chain.doFilter(request, response);

        System.out.println("FontFilter`s doFilter end");
    }

    @Override
    public void destroy() {
        System.out.println("FontFilter destroyed");
    }
}
