package MVC.Controller;

import MVC.Model.UserModel;
import MVC.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterController", value = "/RegisterController")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        User user=new User();
        String password = request.getParameter("password2");
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setUsername(request.getParameter("username"));

        UserModel uModel=new UserModel();
        List<String> errors=uModel.verify(user,password);
        String path;
        if (errors.isEmpty()) {
            uModel.register(user);
           path="RegisterSuccessView";

        } else {
            request.setAttribute("errors", errors);
            path = "RegisterErrorView";
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
