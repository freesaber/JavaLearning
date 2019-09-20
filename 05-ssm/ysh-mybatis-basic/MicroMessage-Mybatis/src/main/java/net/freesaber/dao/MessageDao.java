package net.freesaber.dao;

import net.freesaber.bean.Message;
import net.freesaber.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
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

        // 查询
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行sql语句
            messageList = sqlSession.selectList("Message.queryMessageList");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        // 返回
        return messageList;
    }
}
