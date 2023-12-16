package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import java.sql.SQLException;

public class druidUtil {
    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/webSql?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("sql2008");
    }

    public static DruidPooledConnection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(DruidPooledConnection connection) {
        if (connection != null) {
            try {
                connection.close(); // 归还连接给连接池
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
