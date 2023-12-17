package Filter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HTMLFilter", value = "/HTMLFilter")
public class HTMLFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HTMLFilter initialized");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("HTMLFontFilter`s doFilter start");

        // 设置请求编码
        request.setCharacterEncoding("utf-8");

        // 设置响应编码
        response.setCharacterEncoding(null);
        response.setContentType("text/html");

        // 继续执行过滤器链
        chain.doFilter(request, response);

        System.out.println("HTMLFilter`s doFilter end");
    }

    @Override
    public void destroy() {
        System.out.println("HTMLFilter destroyed");
    }
}
