package MVC.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/RegisterSuccessView")
public class RegisterSuccessView extends HttpServlet {
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>会员注册成功</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>%s会员注册成功</h1>", request.getParameter("username"));
        out.println("<a href='login.html'>返回登录页面</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
