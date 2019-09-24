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

        // 获取SqlSession
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 查询条件
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            // 通过sqlSession执行sql语句
            messageList = sqlSession.selectList("Message.queryMessageList", message);
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

    /**
     * 根据主键删除单条记录
     */
    public void deleteOne(int Id){
        // 获取SqlSession
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行sql语句
            sqlSession.delete("Message.deleteOne", Id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids){
        // 获取SqlSession
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行sql语句
            sqlSession.delete("Message.deleteBatch", ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
