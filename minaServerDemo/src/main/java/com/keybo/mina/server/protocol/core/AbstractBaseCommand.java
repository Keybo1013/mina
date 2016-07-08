package com.keybo.mina.server.protocol.core;

import com.keybo.mina.server.protocol.comm.Constants;
import com.keybo.mina.server.protocol.utils.NumberUtils;

/**
 * socket指令抽象类
 *
 * @author: xiaojian
 * @date: 16-6-22
 * @time: 下午3:58
 * @version: V1.0
 */
public abstract class AbstractBaseCommand implements BaseCommand {

    @Override
    public byte[] toBytes() throws Exception {
        byte[] body = bodyToBytes();
        int len = Constants.COMMAND_LENGTH + body.length;
        byte[] bytes = new byte[len];

        NumberUtils.insert(0, bytes, getCid());//消息类别
        System.arraycopy(body, 0, bytes, Constants.COMMAND_LENGTH, body.length);
        return bytes;
    }

}
