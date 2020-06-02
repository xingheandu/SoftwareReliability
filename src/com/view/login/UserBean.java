package com.view.login;
/*
 *Created by haseeon 2018/12/31.
 */


public class UserBean {
    private int id;
    private String name;
    private String password;

    public UserBean() {
    }

    public UserBean(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
