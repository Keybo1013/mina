package com.keybo.mina.server.protocol.core;

/**
 * socke指令基类
 *
 * @author: xiaojian
 * @date: 16-6-22
 * @time: 下午3:57
 * @version: V1.0
 */
public interface BaseCommand {

    public String chartset = "UTF-8";
//
//    /**
//     * 数据包类型：请求包
//     */
//    public byte TYPE_REQ = 1;
//    /**
//     * 数据包类型：响应包
//     */
//    public byte TYPE_RESP = 2;
//
//    public byte getType();

    public short getCid();

    public void bodyFromBytes(byte[] bytes) throws Exception;

    public byte[] bodyToBytes() throws Exception;

    public byte[] toBytes() throws Exception;

}
