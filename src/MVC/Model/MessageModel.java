package MVC.Model;

import TTA.util.druidUtil;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageModel{

    public List<Message> selectAll(String username){
        List<Message> list=new ArrayList<>();
        DruidPooledConnection connection=null;
        PreparedStatement statement=null;
        ResultSet result=null;
        try{
            connection=Druid.getConnection();
            String sql="select * from messages where username=? order by time desc";
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            result=statement.executeQuery();

            while(result.next()){
                Message m=new Message();
                m.setUsername(result.getString("username"));
                m.setText(result.getString("text"));
                m.setTime(result.getString("time"));
                list.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            druidUtil.close(connection);
        }
        return list;
    }

    public void addMessage(Message message){
        DruidPooledConnection connection=null;
        PreparedStatement statement=null;
        try{
            connection=Druid.getConnection();
            String sql="insert into messages values(?,?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,message.getUsername());
            statement.setString(2,message.getText());
            statement.setString(3, message.getTime());
            statement.executeUpdate();
            System.out.println("message新增成功！");
        } catch (SQLException e) {
            System.out.println("message新增失败！");
            throw new RuntimeException(e);
        }
    }

    public void delMessage(String username,String time){
        DruidPooledConnection connection=null;
        PreparedStatement statement=null;
        try{
            connection=Druid.getConnection();
            String sql="delete from messages where username=? and time=trim(?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,time.trim());
            statement.executeUpdate();
            System.out.println("message删除成功！");
        } catch (SQLException e) {
            System.out.println("message删除失败！");
            throw new RuntimeException(e);
        }
    }
}
