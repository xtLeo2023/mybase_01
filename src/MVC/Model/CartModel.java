package MVC.Model;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartModel {
    public List<CartItem> selectAll(String username) {
        List<CartItem> cart = new ArrayList<>();
        try (
                DruidPooledConnection connection = Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select a.pid, pname, punit, price,path, number from cart a " +
                                "join product b on a.pid=b.pid where a.username=?"
                )
        ) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setPid(result.getInt("pid"));
                    cartItem.setPname(result.getString("pname"));
                    cartItem.setPunit(result.getString("punit"));
                    cartItem.setPrice(result.getDouble("price"));
                    cartItem.setPath(result.getString("path"));
                    cartItem.setNumber(result.getInt("number"));

                    cart.add(cartItem);
                }
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return cart;
    }
    public  void insert(Cart cart){

        try(
                DruidPooledConnection connection= Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement("insert into cart values(?,?,?)");
        ){
            statement.setString(1,cart.getUsername());
            statement.setInt(2, cart.getPid());
            statement.setInt(3,cart.getNumber());

            statement.executeUpdate();
        }catch (SQLException throwAbles){
            throwAbles.printStackTrace();
        }
    }

    public void delete(Cart cart){
        try(
                DruidPooledConnection connection= Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "delete from cart where username=? and pid=?"
                );
        ){
            statement.setString(1,cart.getUsername());
            statement.setInt(2, cart.getPid());

            statement.executeUpdate();
        }catch (SQLException throwAbles){
            throwAbles.printStackTrace();
        }
    }

    public void clear(Cart cart){
        try(
                DruidPooledConnection connection= Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "delete from cart where username=? "
                );
        ){
            statement.setString(1,cart.getUsername());
            statement.executeUpdate();
        }catch (SQLException throwAbles){
            throwAbles.printStackTrace();
        }
    }

    public void reduceNumber(Cart cart){
        try(
                DruidPooledConnection connection= Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "update cart set number=number-1 where number>1 and username=? and pid=?"
                );
        ){
            statement.setString(1,cart.getUsername());
            statement.setInt(2, cart.getPid());

            statement.executeUpdate();
        }catch (SQLException throwAbles){
            throwAbles.printStackTrace();
        }
    }

    public void increaseNumber(Cart cart){
        try(
                DruidPooledConnection connection= Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "update cart set number=number+1 where username=? and pid=?"
                );
        ){
            statement.setString(1,cart.getUsername());
            statement.setInt(2, cart.getPid());

            statement.executeUpdate();
        }catch (SQLException throwAbles){
            throwAbles.printStackTrace();
        }
    }

    public Cart search(Cart cart){
        Cart preCart = new Cart();
        try (
                DruidPooledConnection connection = Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select * from cart where username=? and pid=?"
                )
        ) {
            statement.setString(1, cart.getUsername());
            statement.setInt(2, cart.getPid());
            try (ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    preCart.setUsername(result.getString("username"));
                    preCart.setPid(result.getInt("pid"));
                    preCart.setNumber(result.getInt("number"));
                }else{
                    preCart.setUsername("null");
                }
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return preCart;
    }

    public double getCount(String username){
        double amount=0.00;
        try (
                DruidPooledConnection connection = Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "select sum(number*price) as amount from cart a join product b on a.pid=b.pid where username=?"
                )
        ) {
            statement.setString(1,username);
            try (ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                   amount=result.getDouble("amount");
                }
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return amount;
    }

}
