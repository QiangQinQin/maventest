package com.tulun.maventest;

import java.sql.*;

public class JDBC_Test {
    private static Connection connection;
    public static void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");//用到反射：根据类的包路径创建一个实例
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tulun?useSSL=false", "root", "123456");
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    private static void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectUserById(String id){
        getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql = "select * from student  where sid = "+id;//等于号后面加空格
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer sid = resultSet.getInt("sid");//数据库里的  属性类型  和  属性名（或列数）
                String sex = resultSet.getString("ssex");
                String name = resultSet.getString("sname");
                System.out.println("Id:" + sid + ",name:" + name + "，sex:" + sex);
            }
            close();//自定义的方法
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //变更操作
    private static void updateNameById(String name, String id) {
        getConnection();
        try {
            Statement statement=connection.createStatement();
            //update student set sname = "强钦" where sid =1  //where前面要有空格
            String sql="update student set sname = '"+ name  + " 'where sid = " + id ;//拼接成字符串，‘’表示字符串常量
          //  System.out.println(sql);
            int i=statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    //插入操作
    private static void addStudent( String name,String sex,int age) {
        getConnection();
        try {
            Statement statement=connection.createStatement();
            //insert into  student(SID, Sname, Sage, Ssex)  values (6, '刘万波'  ,22, '男' )
            //建议id (不为空 自增 )
            String sql="insert into  student(SID, Sname, Sage, Ssex)  values ("  + " '"+ name  + "'  ," + age + ", '" + sex  +  "' ) "  ;//拼接成字符串，‘’表示字符串常量
            System.out.println(sql);
            int i=statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    public static  String login(String name,String passwd){
        getConnection();
        try {
            Statement statement=connection.createStatement();
            //and > or
            //sql注入 条件： name= '强钦'  or    1=1 and passwd ='123456' //or前面为真，  or后面不论真假 ,前后or后仍为真，写1=1是为了语法正确
            //  name= '强钦'  #  and passwd ='123456' （#表示注解）
            String sql="select * from user where name= '"+ name +"'or 1=1 and passwd ='"+ passwd+"'"  ;
            System.out.println(sql);
            ResultSet resultSet=statement.executeQuery(sql);
            if(resultSet.next())
                return  "success";
            else
                return "fail";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return "fail";
    }

    //解决sql注入
    public static  String login1(String name,String passwd){
        getConnection();
        try {
            //使用preparedStatement来实现，其中sql中参数用 ？ 占位符
            String sql="select * from  where name =? and passwd =? ";
            PreparedStatement statement=connection.prepareStatement(sql);
            //往sql中占位符给定数据              setString 第一个参数指的是占位符的位置，从1开始
            statement.setString(1,name);//先预编译（检查语法） 再执行
            statement.setString(2,passwd);

            ResultSet resultSet=statement.executeQuery(sql);
            if(resultSet.next())
                return  "success";
            else
                return "fail";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return "fail";
    }

    public static void main(String[] args) {
        //查询操作
//        selectUserById("1");//因为函数参数定义成String类型
        //变更操作
//        updateNameById("强钦","1");
        //增加操作
//        addStudent("Test","男",22);
        //sql注入
        System.out.println(login("强钦","12346"));



      // //加载MYSQL驱动 com.mysql.jdbc.Driver
      //  //8.ji要加com.mysql.cj.jdbc.Driver
//        try {
//            Class.forName("com.mysql.jdbc.Driver");//用到反射：根据类的包路径创建一个实例
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            //连接MYSQL数据库，才能后续操作
//            /**
//             * 连接数据库的信息：
//             *
//             * 1.url:  协议:jdbc  数据库类型：mysql   ip：localhost  端口:3306  数据库名
//             * 2.username:  用户名
//             * 3.password: 密码
//             */
//            //获取Connection
//            // 警告不建议使用没有带服务器身份验证的SSL连接，url中添加useSSL=false;
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tulun?useSSL=false", "root", "123456");
//
//            //获取Statement对象
//            Statement statement = connection.createStatement();
//
//            //查询操作使用executeQuery
//            // String sql = "select * from student  where sid=1";
//            String sql = "select * from student ";
//            ResultSet resultSet = statement.executeQuery(sql);
////            插入和修改
////            statement.executeUpdate();
//            //处理result结果集
//            /**
//             * resultSet对象返回的是一个结果集，可以包含  多个对象
//             * 多个对象中，每调用一次next()方法,就能处理 结果集 中的 一行 数据
//             *
//             */
//            while (resultSet.next()) {
//                Integer id = resultSet.getInt("sid");//数据库里的  属性类型  和  属性名（或列数）
//                String sex = resultSet.getString("ssex");
//                String name = resultSet.getString("sname");
//                System.out.println("Id:" + id + ",name:" + name + "，sex:" + sex);
//            }
//
//            //关闭资源(对象和数据库)
//            connection.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


}
