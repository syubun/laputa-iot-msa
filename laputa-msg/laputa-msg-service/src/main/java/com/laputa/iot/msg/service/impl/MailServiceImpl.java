/*
 *    Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the www.laputa-iot.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: SommerJiang (sommer_jiang@163.com)
 */
package com.laputa.iot.msg.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.constant.CacheConstants;

import com.laputa.iot.msg.api.entity.Mail;

import com.laputa.iot.msg.mapper.MailMapper;
import com.laputa.iot.msg.service.MailService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;



/**
 * 邮件
 *
 * @author Sommer
 * @date 2021-10-20 10:13:09
 */
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements MailService {




    /**
      * 分页查询Mail
      * @param page
      * @param mail 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, Mail mail) {
        IPage<Mail> selectPage = baseMapper.selectPage(page, Wrappers.query(mail));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param mail DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.MAIL_KEY, key = "#mail.id")
    public Boolean saveMail(Mail mail) {

        return this.save(mail);
    }


    /**
     * 通过ID查询Mail
     * @param id mailID
     * @return MailVO
     */
    @Override
    @Cacheable(value = CacheConstants.MAIL_KEY, key = "#id")
    public   Mail selectMailById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除Mail
     * @param id mailID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.MAIL_KEY, key = "#id")
    public Boolean deleteMailById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#mail.id")
    public Boolean updateMail(Mail mail) {
        return this.updateById(mail);
    }





}
