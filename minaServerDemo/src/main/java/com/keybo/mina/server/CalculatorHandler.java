package com.keybo.mina.server;

import com.keybo.mina.server.protocol.core.BaseCommand;
import com.keybo.mina.server.protocol.core.BizHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端IO处理器
 *
 * @author: xiaojian
 * @date: 16-6-22
 * @time: 下午1:32
 * @version: V1.0
 */
public class CalculatorHandler extends IoHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(CalculatorHandler.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.debug("sessionCreated()...sessionId=" + session.getId());
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("sessionOpened()...sessionId=" + session.getId());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.debug("sessionClosed()...sessionId=" + session.getId());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("sessionIdle()...sessionId=" + session.getId());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.debug("messageSent()...sessionId=" + session.getId() + ", message=" + message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.debug("exceptionCaught()..." + cause.getMessage());
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        log.debug("messageReceived()...业务消息处理");
        //业务消息处理
        BizHandler.process(session, (BaseCommand) message);

    }
}
