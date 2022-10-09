package com.tulun.maventest.mapper;

import com.tulun.maventest.pojo.Student;

import java.util.List;
import java.util.Map;

//接口
public interface StudentMapper {
    public List<Student> selectStudentByIds(List<Integer> ids);

    public List<Student>  selectStudentByDIY(Student student);

    public Student selectStudentById(Integer id);

    public List<Student> findAll();

    public int deleteStudentById(Integer id);
   //传多个参数
  // public int updateSnameById(@Param(value="sid")Integer sid,@Param(value="sname") String sname);
     public int updateSnameById(Map map);

     public  int insertStudent(Student student);
}
