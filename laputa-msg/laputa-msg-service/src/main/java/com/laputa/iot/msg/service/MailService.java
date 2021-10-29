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

package com.laputa.iot.msg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.msg.api.entity.Mail;
import com.laputa.iot.msg.api.dto.MailDTO;

/**
 * 邮件
 *
 * @author Sommer
 * @date 2021-10-20 10:13:09
 */
public interface MailService extends IService<Mail> {



    /**
     * 分页查询Mail
     * @param page 分页对象
     * @param mailDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, Mail mailDTO);

    /**
     * 删除Mail
     * @param id
     * @return boolean
     */
    Boolean deleteMailById(Long id);

    /**
     * 更新指定Mail
     * @param mail Mail信息
     * @return
     */
    Boolean updateMail(Mail mail);

    /**
     * 通过ID查询Mail
     * @param id mailID
     * @return MailVO
     */
    Mail selectMailById(Long id);



    /**
     * 保存Mail
     * @param mail DTO 对象
     * @return success/fail
     */
    Boolean saveMail(Mail mail);


}
