/**************************************************************************
 * Copyright (c) 2006-2025 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 *
 * 项目名称：世晨2.0
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。
 ***************************************************************************/
package com.springboot.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:    实体对象公共基础类
 */
public class BaseObject implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 319344584037309250L;



    /** 创建者id.  */
    @TableField(fill = FieldFill.INSERT)
    private String creatorId;


    /** 修改者id.  */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifyId;

    /** 版本信息.   */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 创建时间.
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间.
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


    /**
     * @return the creatorId
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId the creatorId to set
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return the modifyId
     */
    public String getModifyId() {
        return modifyId;
    }

    /**
     * @param modifyId the modifyId to set
     */
    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }


    /**
     * Get 创建时间.
     * @return Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Set 创建时间.
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Get 修改时间.
     * @return Date
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * Set 修改时间.
     * @param modifyTime modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(", \"creatorId\":\"").append(creatorId).append('\"');
        sb.append(", \"modifyId\":\"").append(modifyId).append('\"');
        sb.append(", \"version\":").append(version);
        sb.append('}');
        return sb.toString();
    }
}
