package com.edu.nju.wel.info;

/**
 * Created by zs on 2017/3/14.
 */
public class PersonInfo {
    //对应id
    private int id;
    //姓名-初始为null
    private String name;
    //密码-6~20位
    private String password;
    //7位识别码
    private String code;
    //是否激活-默认为0-未激活
    private int isActive;
    //会员资格状态-恢复0/暂停1/停止2-默认恢复-0
    private int state;

    public PersonInfo() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
