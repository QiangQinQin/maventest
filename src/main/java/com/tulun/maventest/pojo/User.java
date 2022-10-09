package com.tulun.maventest.pojo;

public class User {
    private Integer id;
    private String account;
    private String passwd;
    private Integer sex;
    private String name;

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Integer id, String account, String passwd, Integer sex, String name) {
        this.id = id;
        this.account = account;
        this.passwd = passwd;
        this.sex = sex;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", passwd='" + passwd + '\'' +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
