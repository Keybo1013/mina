package com.keybo.mina.server;

import com.keybo.mina.server.protocol.codec.CommandCodecFactory;
import com.keybo.mina.server.protocol.filter.MyFilter;
import com.keybo.mina.server.protocol.filter.keepalive.KeepAliveMessageFactoryImpl;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author: xiaojian
 * @date: 16-6-22
 * @time: 下午1:40
 * @version: V1.0
 */
public class CalculatorServer {

    private static Logger log = LoggerFactory.getLogger(CalculatorServer.class);

    private static final int PORT = 10010;
    /** 30秒后超时 */
   	private static final int IDELTIMEOUT = 30;

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
//        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDELTIMEOUT);

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("myfliter", new MyFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CommandCodecFactory("UTF-8")));

        KeepAliveMessageFactoryImpl kamfi = new KeepAliveMessageFactoryImpl();
        KeepAliveFilter kaf = new KeepAliveFilter(kamfi, IdleStatus.BOTH_IDLE);
        /** 是否回发 */
        kaf.setForwardEvent(true);
        acceptor.getFilterChain().addLast("heart", kaf);

        acceptor.setHandler(new CalculatorHandler());
        acceptor.bind(new InetSocketAddress(PORT));

        log.debug("socket通信服务端已启动，端口是" + PORT);
    }

}
