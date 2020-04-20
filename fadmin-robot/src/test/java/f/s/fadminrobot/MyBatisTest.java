package f.s.fadminrobot;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


/**
 *
 * @author lijiafu
 * @date 2020/4/4 22:46
 * @since 1.0
 */
public class MyBatisTest {

    @Test
    public void test() throws IOException {
        String resourse = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resourse);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.selectOne("");
        sqlSession.getMapper(SqlSession.class);

    }
}
