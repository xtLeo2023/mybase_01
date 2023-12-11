package MVC.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginSuccessView", value = "/LoginSuccessView")
public class LoginSuccessView extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>会员登录成功</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>你好，%s会员！</h1>", request.getSession().getAttribute("login"));
        out.println("<a href='ProductView'>前往商城</a>");
        out.println("<a href='CartView'>查看购物车</a>");
        out.println("<a href='LogoutController'>注销</a>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }

}
