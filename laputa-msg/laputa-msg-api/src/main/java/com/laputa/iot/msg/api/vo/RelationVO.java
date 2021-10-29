package com.laputa.iot.msg.api.vo;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.msg.api.enums.ContainerEnum;
import com.laputa.iot.upms.api.entity.SysUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName SocialVO
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/13 18:56
 * @Version 1.0.0
 **/
@Data
public class RelationVO implements Serializable {

    private String relId;

    private String relType;

    private Integer relTypeCode;

    private Long userId;

    private Long peerId;

    private String remarkName;

    private String peerType;

    private Integer peerTypeCode;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    private String tag;

    private String peerUserName;

    private String peerGender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String peerPhoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String peerEmail;

    private String peerAvatar;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date peerGmtLastLogin;

    private String peerStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date peerGmtBirthday;

    private String peerNickName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String peerRawRole;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String peerRawAuthority;

    private Set<String> peerRole;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String,Set<String>> peerAuthority;

    private String category;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtLastContact;

    private Integer unReadMsgCount;


    public static RelationVO build(Long userId,   SysUser peer){
        RelationVO relationVO = new RelationVO();
        Long newId= IdWorker.getId();
        relationVO.relId = newId.toString();
        if(userId!=null && userId!= 0L){
            relationVO.userId = userId;
        }
        if(peer!=null){
            relationVO.peerId = peer.getId();
            relationVO.peerType = ContainerEnum.PERSON.getCode().toString();
            relationVO.peerTypeCode = ContainerEnum.PERSON.getCode();
            relationVO.peerUserName = peer.getUsername();
            if(CommonConstants.USER_GENDER_FEMALE.equals(peer.getGender())){
                relationVO.peerGender = "female";
            }else if(CommonConstants.USER_GENDER_MALE.equals(peer.getGender())){
                relationVO.peerGender = "male";
            }else{
                relationVO.peerGender = "male";
            }
            relationVO.peerPhoneNumber = peer.getPhone();
            relationVO.peerEmail = peer.getEmail();
            relationVO.peerAvatar = peer.getAvatar();
            relationVO.peerStatus = peer.getLockFlag();
            relationVO.peerNickName = peer.getName();
            relationVO.peerRawRole = peer.getRole();
            relationVO.peerRawAuthority = peer.getAuthority();
        }
        relationVO.unReadMsgCount = 0;
        return relationVO;
    }

}
