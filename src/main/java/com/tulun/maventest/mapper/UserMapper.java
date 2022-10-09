package com.tulun.maventest.mapper;

import com.tulun.maventest.pojo.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

//    该注解 等价于 UserMapper.xml中<select>标签
//对象的属性名和数据库里的不一样，需要手动映射  Result记得加s  加上仍不可以复用？？？？？只有紧挨写
    @Results(id="userResult",
            value={
                    @Result(column = "id",property = "id"),
                    @Result(column = "account",property = "account"),
                    @Result(column = "passwd",property = "passwd"),
                    @Result(column = "sex",property = "sex"),
                    @Result(column = "name",property = "name"),
            })
    @Select({"select * from user where id=#{id}"})
//    @ResultMap("userResult")
    public User selectUserById(Integer id);


    //注解一定要在他所作用的方法上面
    @Options(useGeneratedKeys=true,keyProperty="id", keyColumn="id")
    @Insert("insert into user(name,passwd,account,sex) values(#{name},#{passwd},#{account},#{sex})")
    public  int addUser(User user);
}
