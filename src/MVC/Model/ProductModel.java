package MVC.Model;

import com.alibaba.druid.pool.DruidPooledConnection;
import TTA.util.druidUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    public  void insert(Product product){

        try(
                DruidPooledConnection connection= Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement("insert into Product values(?,?,?,?,?)");
        ){
            statement.setInt(1,product.getPid());
            statement.setString(2,product.getName());
            statement.setString(3, product.getUnit());
            statement.setFloat(4,product.getPrice());
            statement.setString(5,product.getPath());

            statement.executeUpdate();
        }catch (SQLException throwAbles){
            throwAbles.printStackTrace();
        }
    }

    public List<Product> selectAll(){
        List<Product> products=new ArrayList<>();

        try(
                DruidPooledConnection connection = Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement("select * from product");
                ResultSet result = statement.executeQuery();
        ){

            while(result.next()){
                Product product=new Product();
                product.setPid(result.getInt("pid"));
                product.setName(result.getString("pname"));
                product.setUnit(result.getString("punit"));
                product.setPrice(result.getFloat("price"));
                product.setPath(result.getString("path"));
                products.add(product);
            }

        }catch(SQLException throwAbles){
            throwAbles.printStackTrace();
        }

        return products;
    }
}
