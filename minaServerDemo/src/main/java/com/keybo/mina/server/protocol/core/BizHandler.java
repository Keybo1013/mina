package com.keybo.mina.server.protocol.core;

import org.apache.mina.core.session.IoSession;

/**
 * 业务消息处理器，根据协议号负责生成对应事件T
 *
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 上午11:56
 * @version: V1.0
 */
public class BizHandler {

    /**
     * 消息处理
     *
     * @param session
     * @param command
     * @throws Exception
     */
    public static void process(IoSession session, BaseCommand command) throws Exception {

        Biz biz = CommandFactory.createBiz(command.getCid());
        biz.doBiz(session, command);

    }

}
