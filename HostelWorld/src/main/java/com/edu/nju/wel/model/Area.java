package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zs on 2017/6/26.
 */

@Entity
@Table(name="area")
public class Area  implements Serializable {
    @Id
    private int aId;

    private String name;

    private int pid;

    //类型，最细的为1，大概念为0
    private int type;

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Area{" +
                "aId=" + aId +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", type=" + type +
                '}';
    }
}


