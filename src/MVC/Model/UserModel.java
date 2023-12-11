package MVC.Model;

import com.alibaba.druid.pool.DruidPooledConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserModel {

    private final Pattern emailRegex = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    //example@email.com
    private final Pattern passwdRegex = Pattern.compile("^[\\w]{8,16}$");
    //密码只包含字母、数字和下划线字符，长度在8到16个字符之间
    private final Pattern usernameRegex = Pattern.compile("^[A-Za-z0-9_]{1,16}$");
    //要求用户名长度为1到16个字符，只允许包含字母、数字和下划线。
    private boolean validateEmail(String email) {
        return email != null && emailRegex.matcher(email).find();
    }

    private boolean validateUsername(String username) {
        return username != null && usernameRegex.matcher(username).find();
    }
    private boolean validatePassword(String password1, String password2) {
        return password1 != null && passwdRegex.matcher(password1).find() && password1.equals(password2);
    }

    public List<String> verify(User user,String password){
        List<String> errors = new ArrayList<>();
        if (!validateEmail(user.getEmail())) {
            errors.add("未填写邮件或格式不正确");
        }
        if (!validateUsername(user.getUsername())) {
            errors.add("未填写用户名称或格式不正确");
        }
        if (!validatePassword(user.getPassword(), password)) {
            errors.add("请确认密码符合格式并再次确认密码");
        }
        return errors;
    }

    public void register(User user){
        try (
                DruidPooledConnection connection = Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement("insert into user values(?,?,?,null)")
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

    }

    public boolean login(User user){
        boolean bool = false;

        try (
                DruidPooledConnection connection = Druid.getConnection();
                PreparedStatement statement = connection.prepareStatement("select * from user where username=?")
        ) {
            statement.setString(1, user.getUsername());
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    bool = result.getString("password").equals(user.getPassword());
                }
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }

        return bool;
    }

}
