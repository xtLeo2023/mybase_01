package MVC.View;

import MVC.Model.Message;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "MemberView", value = "/MemberView")
public class MemberView extends HttpServlet {
    private final String LOGIN_PATH = "login.html";
    private SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest (request, response);
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request, response);
    }

    protected void processRequest(
            HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        if (request.getSession().getAttribute("login")== null){
            response.sendRedirect(LOGIN_PATH);
            return;
        }

        String username = getUsername (request);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>memberView</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div height='200px' width='200px'>");
        out.println("<img src='./base/images/avatar.jpg' height='160px' width='160px'>");
        out.println("<a href='LogoutController'>");
        out.println("注销"+username);
        out.println("</a>");
        out.println("</div><div width='400px' height='auto' display='inline-block' >");
        out.println("<form method='post' action='MessageController'>");
        out.println("分享新鲜事...<br>");

        String preText = request.getParameter("text");
        if(preText == null){
            preText ="";
        }else {
            out.println("信息要140字以内<br>");
        }

        out.printf(
                "<textarea cols='60' rows='4' name='text'>%s</textarea><br>",
                preText);

        out.println("<button type='submit'>送出</button>");

        out.println("<table >");
        out.println("<tbody>");

        List<Message> messages =
                (List<Message>) request.getAttribute("messages");
        messages.forEach((message) -> {

            out.println("<tr><td style='vertical-align: top;'>");
            out.printf("%s<br>",username);
            out.printf("%s<br>",message.getText());
            out.printf("%s<br>",message.getTime());

            out.println("<form method='post' action='MessageController'>");
            out.printf(
                    "<input type='hidden' name='time' value='%s'>",message.getTime());
            out.println("<button type='submit'>删除</button>");
            out.println("</form>");

            out.println("<hr></td></tr>");
        });
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    private String getUsername (HttpServletRequest request) {
        return  (String) request.getSession().getAttribute("login");
    }
}
