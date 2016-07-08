package com.keybo.mina.client.protocol.core;

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

    public short getCid();

    public void bodyFromBytes(byte[] bytes) throws Exception;

    public byte[] bodyToBytes() throws Exception;

    public byte[] toBytes() throws Exception;

}
