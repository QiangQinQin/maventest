package com.tulun.maventest;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3p0Demo {
    public static void main(String[] args) {
        //设置数据源（默认位置找）(指定名字就行)
        ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

        try {
            //获取Connection
            Connection connection = dataSource.getConnection();

            //获取Statement对象
            Statement statement = connection.createStatement();

            String sql = "select * from user where id = 1";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String account = resultSet.getString("account");
                String name = resultSet.getString("name");
                System.out.println("Id:" + id1 + ",name:" + name);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
