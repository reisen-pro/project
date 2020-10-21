package com.project.myutil.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author Reisen
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;



    static {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try {
            sqlSessionFactory = builder.build(Resources.getResourceAsReader("SqlMapConfig.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MyBatisUtil() {

    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
