package Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/LogoutController")
public class LogoutController extends HttpServlet {
    private final String LOGIN_PATH = "login.html";
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("login") != null) {
            request.getSession().invalidate();
        }
        response.sendRedirect(LOGIN_PATH);
        return;
    }
}
