package util;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.google.gson.JsonObject;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class paramsUtil {
    private static String params;
    public static void setParams(String newParams) {
        params = newParams;
    }

    // 获取存储过程的参数及类型信息
    public static JsonObject getParams(DruidPooledConnection connection, String procedure) throws SQLException {
        JsonObject paramsInfo = new JsonObject();
        String query = "SELECT parameter_name, data_type FROM information_schema.parameters " +
                "WHERE specific_name = '" + procedure + "' ORDER BY ordinal_position";
        try (ResultSet rs = connection.createStatement().executeQuery(query)) {
            while (rs.next()) {
                String paramName = rs.getString("parameter_name");
                String paramType = rs.getString("data_type");
                paramsInfo.addProperty(paramName, paramType);
            }
        }
        return paramsInfo;
    }

    // 创建CallableStatement并设置参数类型
    public static CallableStatement createCallableStatement(DruidPooledConnection connection, JsonObject params) throws SQLException {
        String procedure = params.get("procedure").getAsString(); // 获取存储过程名称
        JsonObject paramsInfo = getParams(connection, procedure);
        CallableStatement callableStatement =null;
        String callStatement = "{call " + procedure;

        if (!paramsInfo.keySet().isEmpty()) { // 存储过程有参数
            callStatement += "(";
            for (String paramName : paramsInfo.keySet()) {
                if (!paramName.equals("procedure")) { // 排除存储过程名称参数
                    callStatement += "?, ";
                }
            }
            // 移除最后一个逗号和空格，添加结束括号
            callStatement = callStatement.substring(0, callStatement.length() - 2) + ")}";
        } else { // 存储过程没有参数
            callStatement += "()}";
        }

        callableStatement = connection.prepareCall(callStatement);

        int paramIndex = 1;
        for (String paramName : paramsInfo.keySet()) {
            if (!paramName.equals("procedure")) {
                String paramValue = params.get(paramName).getAsString();
                String paramType = paramsInfo.get(paramName).getAsString();

                // 根据参数类型设置不同的方法
                switch (paramType.toLowerCase()) {
                    case "integer":
                        callableStatement.setInt(paramIndex, Integer.parseInt(paramValue));
                        break;
                    case "float":
                        callableStatement.setFloat(paramIndex, Float.parseFloat(paramValue));
                        break;
                    case "double":
                        callableStatement.setDouble(paramIndex, Double.parseDouble(paramValue));
                        break;
                    // 添加更多数据类型的处理
                    default:
                        // 默认情况下，将参数作为字符串设置
                        callableStatement.setString(paramIndex, paramValue);
                        break;
                }

                paramIndex++;
            }
        }

        return callableStatement;
    }
}