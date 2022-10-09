package com.tulun.maventest.mapper;

import com.tulun.maventest.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

//注意名字保持一致（StudentMapper +  Test ）
public class StudentMapperTest {
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

    @Test
    public  void  selectStudentByDIY(){
        System.out.println("selectStudentByDIY()");
        //获取会话
        sqlSession=sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //不传参数
        Student student=new Student();
        System.out.println(mapper.selectStudentByDIY(student));

        //关闭
        sqlSession.commit();//事务
    }

    @Test
    public  void  selectStudentByIds(){
        System.out.println("selectStudentByIds()");
        //获取会话
        sqlSession=sqlSessionFactory.openSession();

        // 不 使用动态代理
        // 由于selectStudentByIds返回结果是多个，所以调用selectList   (给出方法的全路径)
        sqlSession.selectList("com.tulun.maventest.mapper.StudentMapper.selectStudentByIds",new Object[]{1,2,3,4});
        //若查询结果集 是 单个对象  （虽然只需要id,但是可以传一个对象进去）
        Student student = new Student();
        student.setSID(1);
        sqlSession.selectOne("com.tulun.maventest.mapper.StudentMapper.selectStudentById",student);

        //通过动态代理在执行过程中产生对象  org.apache.ibatis.binding.MapperRegistry#getMapper
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        mapper.selectStudentByIds(ids);
        //关闭
        sqlSession.commit();//事务
    }

    @Test //表示要进行测试
    public void selectStudentById() {
        System.out.println("selectStudentById()");
        //获取会话
         sqlSession=sqlSessionFactory.openSession();

//         原始通过方法直接调用  statement中指定方法的全路径名
        Student student1 =sqlSession.selectOne("com.tulun.maventest.mapper.StudentMapper.selectStudentById",1);
        System.out.println(student1);
        //        //通过反射机制来获取到mapper实例
//        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//
//        Student student = mapper.selectStudentById(1);
//        System.out.println(student);
    }

//    @Test
//    public void findAll() {
//        System.out.println("findAll");
//        //获取会话
//        sqlSession=sqlSessionFactory.openSession();
//
////        返回多个对象selectList
//        List<Student> students1  =sqlSession.selectList("com.tulun.maventest.mapper.StudentMapper.findAll");
//        for(Student s:students1)
//            System.out.println(s);
//
////        //通过反射机制来获取到mapper实例
////        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
//
////        List<Student> students = mapper.findAll();//该接口方法返回的就是List<Student>
////        for(Student s:students)
////            System.out.println(s);
//    }


//    @Test
//    public void deleteStudentById() {
//    }
//
//    @Test
//    public void updateSnameById() {
//    }
//
    @Test
    public void insertStudent() {
        System.out.println("insertStudent()");
        //获取会话
        sqlSession=sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //第二次插就会元素重复
        Student student=new Student(11,"刘元龙",24,"男");//和构造函数连顺序都对应上了
        System.out.println(mapper.insertStudent(student));//不关注返回结果

        //关闭
        sqlSession.commit();//事务
    }

    @After
    public void  after(){
        System.out.println("after");
        sqlSession.close();
    }
}