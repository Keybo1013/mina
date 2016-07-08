package com.keybo.mina.server.protocol.core;

import com.keybo.mina.server.protocol.utils.DateUtils;

import java.io.Serializable;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午2:56
 * @version: V1.0
 */
public class CommandRespMsg implements Serializable {

    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 响应码
     */
    private int respCode;
    /**
     * 响应消息
     */
    private Object data;

    public CommandRespMsg() {
        this.sendTime = DateUtils.getCurrDateStr();
    }

    public CommandRespMsg(int respCode) {
        this.sendTime = DateUtils.getCurrDateStr();
        this.respCode = respCode;
    }

    public CommandRespMsg(int respCode, Object data) {
        this.sendTime = DateUtils.getCurrDateStr();
        this.respCode = respCode;
        this.data = data;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
