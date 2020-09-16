package com.springboot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动补充插入或更新时的值.
 * @author  <a href="huangxy@smartebao.com">huangxy</a>
 * @version $Id: MyMetaObjectHandler.java  2020/9/16 2:41 下午  huangxy $
 * @since 2.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createId","111",metaObject);
        this.setFieldValByName("createTime", new Date(),metaObject);
        this.setFieldValByName("modifyId","111",metaObject);
        this.setFieldValByName("modifyTime",new Date(),metaObject);
        this.setFieldValByName("version", Integer.valueOf(1),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyId","111",metaObject);
        this.setFieldValByName("modifyTime",new Date(),metaObject);

        //this.setFieldValByName("version", (Integer)this.getFieldValByName("version",metaObject)+1,metaObject);

    }
}
