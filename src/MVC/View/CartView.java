package MVC.View;

import MVC.Model.CartItem;
import MVC.Model.CartModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CartView", value = "/CartView")
public class CartView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("login");
        CartModel cModel = new CartModel();
        List<CartItem> items = cModel.selectAll(username);
        CartItem item;
        // 使用 String.format 保留两位小数
        String total = String.format("%.2f", cModel.getCount(username));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>商城</title>");
        out.println("<style>");
        out.println("body { text-align: center; }");
        out.println(".scrollable-table { width: 80%; margin: auto; height: 75%; overflow: auto;border-bottom:solid 1px #8c8c8c; }"); // 设置所需的高度
        out.println("table { width: 100%; margin: auto; font-family: Arial, sans-serif; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid #dddddd; text-align: center; padding: 8px; min-width: 100px;}");
        out.println("th { background-color: #f2f2f2; }");
        out.println("img { max-width: 100%; max-height: 100%; display: block; margin: auto; }");
        out.println(".user-info { text-align: left; margin-left: 10%; margin-bottom: 20px; }");
        out.println(".user-info a { margin-right: 20px; }");
        out.println("</style>");

        out.println("<script>");
        out.println("function clearCart() {");
        out.println("  if (confirm('确定清空购物车吗？')) {");
        out.println("    document.getElementById('clearCartForm').submit();");
        out.println("  }");
        out.println("}");
        out.println("</script>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='user-info'>");
        out.printf("<h1>你好，%s会员！</h1>", request.getSession().getAttribute("login"));
        out.println("<a href='ProductView'>前往商城</a>");
        out.println("<a href='LogoutController'>注销</a>");
        out.println("</div>");

        out.println("<div class='scrollable-table'>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>图片</th>");
        out.println("<th>商品名称</th>");
        out.println("<th>规格</th>");
        out.println("<th>价格</th>");
        out.println("<th>数量</th>");
        out.println("<th>操作</th>");
        out.println("</tr>");
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            out.println("<tr>");
            out.println("<td><img src='./base/images/" + item.getPath() + "'  height='160px' width='160px'></td>");
            out.println("<td>" + item.getPname() + "</td>");
            out.println("<td>" + item.getPunit() + "</td>");
            out.println("<td>" + item.getPrice() + "</td>");
            out.println("<td>");

            // 将减号按钮、数量和加号按钮放在同一单元格内的 <div> 元素中
            out.println("<div style='display: flex; align-items: center; text-align: center; justify-content: center;'>");

            // 减号按钮
            out.println("<form action='CartController' method='post' style='margin: 0;'>");
            out.println("<input type='hidden' name='dose' value='reduce'>");
            out.println("<input type='hidden' name='username' value='" + username + "'>");
            out.println("<input type='hidden' name='pid' value='" + item.getPid() + "'>");
            out.println("<input type='hidden' name='number' value='" + item.getNumber() + "'>");
            out.println("<button type='submit' >减</button>");
            out.println("</form>");

            // 显示数量
            out.println("<span style='margin: 0 15px;'>" + item.getNumber() + "</span>");

            // 加号按钮
            out.println("<form action='CartController' method='post' style='margin: 0;'>");
            out.println("<input type='hidden' name='dose' value='increase'>");
            out.println("<input type='hidden' name='username' value='" + username + "'>");
            out.println("<input type='hidden' name='pid' value='" + item.getPid() + "'>");
            out.println("<input type='hidden' name='number' value='" + item.getNumber() + "'>");
            out.println("<button type='submit'>加</button>");
            out.println("</form>");

            out.println("</div>");

            out.println("</td>");
            out.println("<td>");
            // 添加删除商品的表单
            out.println("<form action='CartController' method='post' style='margin: 0;'>");
            out.println("<input type='hidden' name='dose' value='del'>");
            out.println("<input type='hidden' name='username' value='" + username + "'>");
            out.println("<input type='hidden' name='pid' value='" + item.getPid() + "'>");
            out.println("<input type='hidden' name='number' value='" + item.getNumber() + "'>");
            out.println("<button type='submit'>删除商品</button>");
            out.println("</form>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");

        out.println("<div style='text-align: right; margin-right:10.5%; margin-left:10.5%; margin-top: 20px; '>");
        out.println("<span style='font-size:20px; font-weight:bold; float:left;'>总计："+total+"</span>");
        out.println("<form id='clearCartForm' action='CartController' method='post' style='display: inline;'>");
        out.println("<input type='hidden' name='dose' value='clear'>");
        out.println("<input type='hidden' name='username' value='" + username + "'>");
        out.println("<button type='button' onclick='clearCart()' style='width:60px; height:30px; font-size:16px;'>结算</button>");
        out.println("</form>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
