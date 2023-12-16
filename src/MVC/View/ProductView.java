package MVC.View;

import MVC.Model.Product;
import MVC.Model.ProductModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductView", value = "/ProductView")
public class ProductView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductModel pModel = new ProductModel();
        List<Product> products = pModel.selectAll();
        Product product;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>商城</title>");

        out.println("<style>");
        out.println("body { text-align: center; }");
        out.println(".scrollable-table { width: 80%; margin: auto; height: 75%; overflow: auto; border-bottom:solid 1px #dddddd; }"); // 设置所需的高度
        out.println("table { width: 100%; margin: auto; font-family: Arial, sans-serif; border-collapse: collapse; overflow: auto; }");
        out.println("th, td { border: 1px solid #dddddd; text-align: center; padding: 8px; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("img { max-width: 100%; max-height: 100%; display: block; margin: auto; }");
        out.println(".user-info { text-align: left; margin-left: 10%; margin-bottom: 20px; }");
        out.println(".user-info a { margin-right: 20px; }");
        out.println(".addToCartForm { display: inline; }");
        out.println("</style>");

        out.println("<script>");
        out.println("function showConfirmation() {");
        out.println("  alert('商品已添加到购物车！');");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='user-info'>");
        out.printf("<h1>你好，%s会员！</h1>", request.getSession().getAttribute("login"));
        out.println("<a href='CartView'>查看购物车</a>");
        out.println("<a href='LogoutController'>注销</a>");
        out.println("</div>");

        out.println("<div class='scrollable-table'>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>图片</th>");
        out.println("<th>商品名称</th>");
        out.println("<th>规格</th>");
        out.println("<th>价格</th>");
        out.println("<th>操作</th>");
        out.println("</tr>");
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);
            out.println("<tr>");
            out.println("<td><img src='./base/images/" + product.getPath() + "'  height='160px' width='160px'></td>");
            out.println("<td>" + product.getName() + "</td>");
            out.println("<td>" + product.getUnit() + "</td>");
            out.println("<td>" + product.getPrice() + "</td>");
            out.println("<td>");
            out.println("<form class='addToCartForm' action='CartController' method='post' onsubmit='showConfirmation()'>");
            out.println("<input type='hidden' name='dose' value='add'>");
            out.println("<input type='hidden' name='username' value='" + request.getSession().getAttribute("login") + "'>");
            out.println("<input type='hidden' name='pid' value='" + product.getPid() + "'>");
            out.println("<input type='hidden' name='number' value='1'>");
            out.println("<button type='submit' >加入购物车</button>");
            out.println("</form>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
