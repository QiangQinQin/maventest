package com.tulun.maventest.mapper;

import com.tulun.maventest.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    @Before
    public void  before(){
        System.out.println("before");
        String resource="mybatis-config.xml";
        try {
            //mybatis提供Resources来获取配置文件流
            InputStream resourceAsStream=Resources.getResourceAsStream(resource);
            //创建会话工厂
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void selectUserById() {
//        System.out.println("selectUserById()");
//        //获取会话
//         sqlSession=sqlSessionFactory.openSession();
//
////         原始通过方法直接调用  statement中指定方法的全路径名
//        User user =sqlSession.selectOne("com.tulun.maventest.mapper.UserMapper.selectUserById",1);
//        System.out.println(user);
//        //        //通过反射机制来获取到mapper实例
////        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
////
////        Student student = mapper.selectStudentById(1);
////        System.out.println(student);
//    }

    @Test
    public void addUserTest() {
        //获取会话
        sqlSession=sqlSessionFactory.openSession();

       UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user=new User();
        user.setName("刘洋");
        user.setPasswd("145");
        user.setAccount("0146789");
        user.setSex(3);
        user.setId(8);
       int user1=mapper.addUser(user);
        System.out.println(user1);
    }

    @After
    public void  after(){
        System.out.println("after");
        sqlSession.commit();//事务
        sqlSession.close();
    }
}