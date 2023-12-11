package MVC.Controller;

import MVC.Model.Message;
import MVC.Model.MessageModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@WebServlet(name = "MemberController", value = "/MemberController")
public class MemberController extends HttpServlet {
    private final String MEMBER_PATH = "MemberView";
    private final String LOGIN_PATH = "login.html";
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("login")== null) {
            response.sendRedirect(LOGIN_PATH);
            return;
        }
        MessageModel model=new MessageModel();
        List<Message> messages=model.selectAll(getUsername(request));
        request.setAttribute("messages", messages);
        request.getRequestDispatcher(MEMBER_PATH).forward(request, response);
    }
    private String getUsername (HttpServletRequest request) {
        return  (String) request.getSession().getAttribute("login");
    }


}

