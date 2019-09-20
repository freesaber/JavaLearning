package net.freesaber.dao;

import net.freesaber.bean.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 和message表相关的数据库操作
 */
public class MessageDao {
    // 根据查询条件查询消息列表
    public List<Message> queryMessageList(String command, String description) {
        // 返回值
        List<Message> messageList = new ArrayList<>();
        try {
            // 数据库连接
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8&useSSL=false", "root", "passok");
            // 查询脚本
            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where");
            List<String> paramList = new ArrayList<>();
            if (command != null && !"".equals(command.trim())) {
                sql.append(" COMMAND=? and");
                paramList.add(command);
            }
            if (description != null && !"".equals(description.trim())) {
                sql.append(" DESCRIPTION like '%' ? '%' and");
                paramList.add(description);
            }
            String strSql = sql.toString();
            if (paramList.size() > 0) {
                strSql = strSql.substring(0, strSql.length() - 3);
            } else {
                strSql = strSql.substring(0, strSql.length() - 5);
            }
            // 查询条件
            PreparedStatement statement = conn.prepareStatement(strSql);
            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i + 1, paramList.get(i));
            }
            // 获取结果
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
                messageList.add(message);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
