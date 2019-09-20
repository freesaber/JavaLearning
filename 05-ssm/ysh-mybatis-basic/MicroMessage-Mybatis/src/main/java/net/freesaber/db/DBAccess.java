package net.freesaber.db;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

/**
 * 访问数据库类
 */
public class DBAccess {
    public SqlSession getSqlSession() throws IOException {
        // 通过配置文件获取数据库连接信息，将异常抛给dao层，dao层关闭SqlSession
        Reader reader = Resources.getResourceAsReader("Configuration.xml");

        // 通过配置信息构建一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 通过sqlSessionFactory打开一个数据库的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
