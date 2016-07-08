package com.keybo.mina.client;

import com.keybo.mina.client.protocol.core.BaseCommand;
import com.keybo.mina.client.protocol.core.BizHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: xiaojian
 * @date: 16-1-13
 * @time: 下午3:18
 * @version: V1.0
 */
public class CalculatorClientHander extends IoHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(CalculatorClientHander.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        //业务消息处理
        BizHandler.process(session, (BaseCommand) message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.debug("client发送信息" + message.toString());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.debug("client与:" + session.getRemoteAddress().toString() + "断开连接");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.debug("client与:" + session.getRemoteAddress().toString() + "建立连接");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("IDLE " + session.getIdleCount(status));
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("打开连接");
    }
}
