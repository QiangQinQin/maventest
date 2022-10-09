package com.tulun.maventest.pojo;
/*
 和数据库中的student表对应，将一行数据  映射成一个对象
 */
public class Student {
    private Integer SID;
    private Integer id;//和数据库名字不一样了，需要在StudentMapper.xml文件里自定义映射
    private String Sname;
    private String Ssex;
    private Integer Sage;

    public Student() {
    }
//    public Student(Integer id, String sname, Integer sage, String ssex) {
//        this.id = id;
//        Sname = sname;
//        Ssex = ssex;
//        Sage = sage;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", Sname='" + Sname + '\'' +
//                ", Ssex='" + Ssex + '\'' +
//                ", Sage=" + Sage +
//                '}';
//    }
        public Student(Integer SID, String sname, Integer sage, String ssex) {
        this.SID = SID;
        Sname = sname;
        Ssex = ssex;
        Sage = sage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "SID=" + SID +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sage=" + Sage +
                '}';
    }

    public Integer getSID() {
        return SID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public Integer getSage() {
        return Sage;
    }

    public void setSage(Integer sage) {
        Sage = sage;
    }
}
