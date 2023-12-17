package Controller;

import Model.Product;
import Model.ProductModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "ProductController", value = "/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class ProductController extends HttpServlet {
    private static final String UPLOAD_DIR = "base/images/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String pid = request.getParameter("pid");
        String name = request.getParameter("pname");
        String unit = request.getParameter("punit");
        String price = request.getParameter("price");

        Part filePart = request.getPart("image"); // 假设文件输入的名称是 "image"
        String extension = getExtension(filePart);
        String fileName = setFileName(pid,extension);

        Product product = new Product();
        product.setPid(Integer.parseInt(pid));
        product.setName(name);
        product.setUnit(unit);
        product.setPrice(Float.parseFloat(price));
        product.setPath(product.getPid() + "." + extension); // 设置为产品编码 + 图片类型

        ProductModel pModel = new ProductModel();
        pModel.insert(product);

        // 设置保存路径，获取Web应用程序根目录
        String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIR;
        // 将文件保存到指定文件夹
        saveFile(filePart, fileName, uploadPath);

        // 转发或重定向到您想要的视图
        response.sendRedirect("ProductView");
    }

    private String setFileName(String pid, String extension) {
        return pid + "." + extension;
    }

    // 获取上传文件的扩展名
    private String getExtension(Part part) {
        String submittedFileName = part.getSubmittedFileName();
        return submittedFileName.substring(submittedFileName.lastIndexOf(".") + 1);
    }

    // 保存文件到指定路径
    private void saveFile(Part part, String fileName, String uploadPath) throws IOException {
        try (InputStream input = part.getInputStream()) {
            // 拼接文件路径
            Path filePath = Paths.get(uploadPath, fileName);
            // 复制文件到指定路径，替换已存在的文件
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
