package com.laputa.iot.common.data.mybatis;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import com.laputa.iot.common.core.base.entity.SuperEntity;
import com.laputa.iot.common.core.base.id.IdGenerate;

import com.laputa.iot.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;


import java.time.LocalDateTime;


/**
 * MyBatis Plus 元数据处理类
 * 用于自动 注入 id, createTime, updateTime, createUser, updateUser 等字段
 *
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * id类型判断符
     */
    private final static String ID_TYPE = "java.lang.String";
    /**
     * 实体类型判断符
     */
    private final IdGenerate<Long> idGenerator;

    public MyMetaObjectHandler(IdGenerate<Long> idGenerator) {
        super();
        this.idGenerator = idGenerator;
    }



    /**
     * 所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： 会通过IdGenerate生成唯一ID
     * createUser, updateUser: 自动赋予 当前线程上的登录人id
     * createTime, updateTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean flag = true;

//        log.info("userid: {}",BaseContextHandler.getUserId());
//        log.info("laputaUser = {}",(Objects.requireNonNull(getUsername())));

//        log.info("laputaUser = {}",(Objects.requireNonNull(SecurityUtils.getUser())));
        Long userId = 1l;
        if(SecurityUtils.getAuthentication()!=null){
            Authentication authentication = SecurityUtils.getAuthentication();

            if(!authentication.getName().equals("anonymousUser")){
//                log.info("laputaUser = {}",SecurityUtils.getUser());
                userId = SecurityUtils.getUser().getId();
            }
        }
        if (metaObject.getOriginalObject() instanceof SuperEntity) {
            Object oldId = ((SuperEntity) metaObject.getOriginalObject()).getId();
            if (oldId != null) {
                flag = false;
            }

            SuperEntity entity = (SuperEntity) metaObject.getOriginalObject();
            if (entity.getCreateTime() == null) {
                this.setFieldValByName(BaseEntity.CREATE_TIME, LocalDateTime.now(), metaObject);
            }
            if (entity.getCreateUser() == null || entity.getCreateUser().equals(0)) {
                if (ID_TYPE.equals(metaObject.getGetterType(SuperEntity.CREATE_USER).getName())) {

                    this.setFieldValByName(BaseEntity.CREATE_USER, String.valueOf(userId), metaObject);
                } else {
                    this.setFieldValByName(BaseEntity.CREATE_USER, userId, metaObject);
                }
            }


        }

        if (flag) {
            Long id = idGenerator.generate();
            if (ID_TYPE.equals(metaObject.getGetterType(SuperEntity.FIELD_ID).getName())) {
                this.setFieldValByName(SuperEntity.FIELD_ID, String.valueOf(id), metaObject);
            } else {
                this.setFieldValByName(SuperEntity.FIELD_ID, id, metaObject);
            }
        }

        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
            update(metaObject, entity);

        }





    }

    private void update(MetaObject metaObject, BaseEntity entity, String et) {
        Long userId = 1l;
        if(SecurityUtils.getAuthentication()!=null){
            Authentication authentication = SecurityUtils.getAuthentication();

            if(!authentication.getName().equals("anonymousUser")){
//                log.info("laputaUser = {}",SecurityUtils.getUser());
                userId = SecurityUtils.getUser().getId();
            }
        }
        if (entity.getUpdateUser() == null || entity.getUpdateUser().equals(0)) {
            if (ID_TYPE.equals(metaObject.getGetterType(et + BaseEntity.UPDATE_USER).getName())) {
                this.setFieldValByName(BaseEntity.UPDATE_USER, String.valueOf(userId), metaObject);
            } else {
                this.setFieldValByName(BaseEntity.UPDATE_USER, userId, metaObject);
            }
        }

        if (entity.getVersion() == null || entity.getVersion().equals(0)) {
            this.setFieldValByName(BaseEntity.VERSION, 1, metaObject);
        }

        if (entity.getUpdateTime() == null) {
            this.setFieldValByName(BaseEntity.UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }

    private void update(MetaObject metaObject, BaseEntity entity) {
        update(metaObject, entity, "");
    }

    /**
     * 所有的继承了Entity、SuperEntity的实体，在update时，
     * updateUser: 自动赋予 当前线程上的登录人id
     * updateTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        if (metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
            update(metaObject, entity);
        } else {
            //updateById updateBatchById update(T entity, Wrapper<T> updateWrapper);
            Object et = metaObject.getValue(Constants.ENTITY);
            if (et != null && et instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) et;
                update(metaObject, entity, Constants.ENTITY + ".");
            }
        }
    }
}