package com.springboot.pojo;

import java.io.Serializable;

/**
 * @author huang xiao 2
 */
public class Student implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 性别,1-男，0-女
     */
    private String sex;

    /**
     * 分数
     */
    private Integer score;


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    private static final long serialVersionUID = 1L;
}