package Controller;

import Model.User;
import Model.UserModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user=new User();
        user.setPassword(request.getParameter("password"));
        user.setUsername(request.getParameter("username"));

        UserModel uModel=new UserModel();
        boolean bool=uModel.login(user);
        String path;
        if(bool){
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            request.getSession().setAttribute("login", user.getUsername());
            path = "ProductView";
            response.sendRedirect(path);
        }
        else {
            path="LoginErrorView";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

