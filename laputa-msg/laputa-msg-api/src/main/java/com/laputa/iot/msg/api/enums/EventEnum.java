package com.laputa.iot.msg.api.enums;

/**
 * @ClassName EventEnum
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/8 15:54
 * @Version 1.0.0
 **/
public enum EventEnum {

    EVENT_CHAT_JOIN("E1001", "新人加入"),
    EVENT_CHAT_LEAVE("E1002", "有人离开"),
    EVENT_CHAT_CHANGED("E1003", "Room被改变"),
    EVENT_CHAT_MESSAGE("E1004", "新消息"),
    EVENT_RECENT_CHANGED("E1005", "最近联系人列表改变"),
    EVENT_UNREAD_CHANGED("E1006", "未读信息计数改变"),
    EVENT_CONSPICUOUS_NOTIFY("E1007", "显示消息"),
    EVENT_CALL_RING("E2001", "呼叫响铃"),
    EVENT_CALL_IN("E2002", "有新呼叫"),
    EVENT_CALL_FAIL("E2003", "呼叫失败"),
    EVENT_CALL_BUSY("E2004", "对方忙"),
    EVENT_CALL_OFFLINE("E2005", "对方不在线"),
    EVENT_CALL_DENY("E2006", "呼叫被拒绝"),
    EVENT_CALL_PRE_CONNECT("E2007", "会话连接建立中"),
    EVENT_CALL_CONNECTED("E2008", "呼叫双方通话中"),
    EVENT_CALL_HANGUP("E2009", "呼叫对放挂断"),
    EVENT_CONNECT("E2011", "第一次(或重连)初始化连接"),
    EVENT_CHAT("E2012", "聊天消息"),
    EVENT_SIGNED("E2013", "消息签收"),
    EVENT_KEEPALIVE("E2014", "客户端保持心跳"),
    EVENT_NOTICEREQUEST("E2015", "申请好友、群组请求"),
    EVENT_NEWFRIEND("E2016","通知好友添加至列表"),
    EVENT_ONLINE("E2017","通知好友我在线了"),
    EVENT_OFFLINE("E2018","通知好友我离线了"),
    EVENT_NEWGROUP("E2019","通知好友刷新群列表"),
    EVENT_ADMIN_CONNECT("E2020","管理员连接"),
    EVENT_REJECTUSER("E2021","踢出用户"),
    SQUEEZE_OFFLINE("E2022","您的账号已在其他地方登录"),
    EVENT_NEW_MESSAGE_NOTIFY("E2010", "全局消息通知");



    private String code;
    private String desc;

    EventEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return desc;
    }
}
