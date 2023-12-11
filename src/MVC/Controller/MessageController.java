package MVC.Controller;

import MVC.Model.Message;
import MVC.Model.MessageModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "MessageController", value = "/MessageController")
public class MessageController extends HttpServlet {
    private final String MEMBER_PATH = "MemberController";
    SimpleDateFormat f = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username=(String) request.getSession().getAttribute("login");
        if(username==null){
            response. sendRedirect ("login.html");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        String time=request.getParameter("time");
        MessageModel model=new MessageModel();

        if(text!=null && text.length()>0){
            if(text.length() <= 140){
                Message m=new Message();
                m.setUsername(username);
                m.setText(text);
                m.setTime(f.format(new Date()));
                model.addMessage(m);
                response.sendRedirect(MEMBER_PATH);
            }else{
                System.out.println("字数请保持在140以内！");
                request.getRequestDispatcher (MEMBER_PATH)
                        .forward(request, response);
            }
        }else if(time!=null){
            model.delMessage(username,time);
            response.sendRedirect(MEMBER_PATH);
        }else{
            System.out.println("未知操作！");
            response.sendRedirect(MEMBER_PATH);
            return;
        }


    }
}
