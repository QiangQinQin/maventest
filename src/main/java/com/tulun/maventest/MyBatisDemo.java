package com.tulun.maventest;

import com.tulun.maventest.mapper.StudentMapper;
import com.tulun.maventest.pojo.Student;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyBatisDemo {
    public static void main(String[] args) {
        select();
//        delete();
//        updateSnameById();
//        insertStudent();
    }
    //查询操作
    private static void select() {

        try {
            String resource="mybatis-config.xml";
            //mybatis提供Resources来获取配置文件流
            InputStream resourceAsStream=Resources.getResourceAsStream(resource);

            //创建会话工厂
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);

            //获取会话
            SqlSession sqlSession=sqlSessionFactory.openSession();

            //通过反射机制来获取到mapper实例
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            Student student = mapper.selectStudentById(1);
            System.out.println(student);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除操作
    private static void delete() {

//      // 方法2：  通过java代码的形式将配置信息给定 (伪代码)
//        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
//        //  DataSourceFactory dataSourceFactory = new DataSourceFactory();
//        //包路径：org.apache.ibatis.mapping.Environment;
//        Environment development = new Environment("development", jdbcTransactionFactory,数据源);
//        Configuration configuration = new Configuration(development);
//        configuration.addMapper(StudentMapper.class);
//        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);



        try {
       //方法1： 通过读取配置文件
            String resource="mybatis-config.xml";
            //mybatis提供Resources来获取配置文件流
            InputStream resourceAsStream=Resources.getResourceAsStream(resource);
            //创建会话工厂
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(resourceAsStream);

            //获取会话
            SqlSession sqlSession=sqlSessionFactory.openSession();

            //StudentMapper是接口，mapper是系统创建的虚拟的实现类
            //   通过动态代理（重点）机制(基于反射) 产生了一个代理类
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            //
            System.out.println(mapper.deleteStudentById(3));//不关注返回结果

            //关闭
            sqlSession.commit();//事务
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        //修改操作
        private static void updateSnameById() {

            try {
                String resource = "mybatis-config.xml";//会默认以resources为根目录在下面找
                //mybatis提供Resources来获取配置文件流
                InputStream resourceAsStream = Resources.getResourceAsStream(resource);

                //创建会话工厂
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

                //获取会话
                SqlSession sqlSession = sqlSessionFactory.openSession();

                //StudentMapper是接口，mapper是系统创建的虚拟的实现类
                //   通过动态代理（重点）机制(基于反射) 产生了一个代理类
                StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

//                System.out.println(mapper.updateSnameById(4, "刘心晶"));
                Map<String,String> map=new HashMap<String,String>();
                map.put("sid","4");
                map.put("sname","刘心");
                System.out.println(mapper.updateSnameById(map));

                //关闭
                sqlSession.commit();//事务
                sqlSession.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    //插入操作
    private static void insertStudent() {

        try {
            String resource = "mybatis-config.xml";
            //mybatis提供Resources来获取配置文件流
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);

            //创建会话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

            //获取会话
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //StudentMapper是接口，mapper是系统创建的虚拟的实现类
            //   通过动态代理（重点）机制(基于反射) 产生了一个代理类
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            Student student=new Student(10,"涂圣佑",24,"男");//和数据库连顺序都对应上了
            System.out.println(mapper.insertStudent(student));//不关注返回结果

            //关闭
            sqlSession.commit();//事务
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
