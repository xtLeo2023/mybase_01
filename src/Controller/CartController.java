package Controller;

import Model.Cart;
import Model.CartModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartController", value = "/CartController")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String dose = request.getParameter("dose");
        String username = request.getParameter("username");
        int pid = 0;
        int number = 0;

        // 创建一个 Cart 对象，使用 CartModel 将其插入到数据库中
        Cart cart = new Cart(username, pid, number);
        CartModel cartModel = new CartModel();

        if(dose.equals("clear")){
            cartModel.clear(cart);
            response.sendRedirect("CartView");
        }else{

            pid = Integer.parseInt(request.getParameter("pid"));
            number = Integer.parseInt(request.getParameter("number"));
            cart.setPid(pid);
            cart.setNumber(number);

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

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
