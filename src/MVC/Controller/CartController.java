package MVC.Controller;

import MVC.Model.Cart;
import MVC.Model.CartModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartController", value = "/CartController")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String dose = request.getParameter("dose");
        String username = request.getParameter("username");
        int pid = Integer.parseInt(request.getParameter("pid"));
        int number = Integer.parseInt(request.getParameter("number"));

        // 创建一个 Cart 对象，使用 CartModel 将其插入到数据库中
        Cart cart = new Cart(username, pid, number);
        CartModel cartModel = new CartModel();

        if (dose.equals("add")) {
            Cart result=cartModel.search(cart);
            if(!result.getUsername().equals("null")){
                cartModel.increaseNumber(cart);
            }else{
                cartModel.insert(cart);
            }
            response.sendRedirect("ProductView");
        } else if (dose.equals("del")) {
            cartModel.delete(cart);
            response.sendRedirect("CartView");
        } else if (dose.equals("reduce")) {
            cartModel.reduceNumber(cart);
            response.sendRedirect("CartView");
        } else if (dose.equals("increase")) {
            cartModel.increaseNumber(cart);
            response.sendRedirect("CartView");
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
