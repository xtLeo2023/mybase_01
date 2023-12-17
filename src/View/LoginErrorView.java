package View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginErrorView", value = "/LoginErrorView")
public class LoginErrorView extends HttpServlet {
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>会员登录失败</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>会员登录失败</h1>");
        out.println("<p>密码错误或用户未注册？</p>");
        out.println("</ul>");
        out.println("<a href='login.html'>返回登录页面</a>");
        out.println("<a href='register.html'>前往注册页面</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
