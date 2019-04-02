package cn.miao.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private static SqlSessionFactory factory;

    //    在静态代码块中创建SqlsessionFactory可保证，只被创建一次
    static {

        try {
            //1、获取mybatis-config.xml 的输入流
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            //2、创建SqlsessionFactory对象，完成对配置文件的读取
            factory = new SqlSessionFactoryBuilder().build(is);
//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession createSqlSession() {
        //  开启事务控制
        return factory.openSession(false);
    }


    //关闭SqlSession
    public static void closeSqlSession(SqlSession sqlSession) {

        if (sqlSession != null) {
            sqlSession.close();
        }
    }


}
