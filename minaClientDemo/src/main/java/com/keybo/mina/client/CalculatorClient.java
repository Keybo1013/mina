package com.keybo.mina.client;

import com.keybo.mina.client.protocol.codec.CommandCodecFactory;
import com.keybo.mina.client.protocol.filter.keepalive.KeepAliveMessageFactoryImpl;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author: xiaojian
 * @date: 16-6-22
 * @time: 下午2:17
 * @version: V1.0
 */
public class CalculatorClient implements Runnable {

    private static Logger log = LoggerFactory.getLogger(CalculatorClient.class);

    private static final String IP = "192.168.1.231";
    private static final int PORT = 10010;
    private static final int CONNECT_TIMEOUT = 60 * 1000;

    private int clientCount;

    public CalculatorClient(int clientCount) {
        this.clientCount = clientCount;
    }

    @Override
    public void run() {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);//设置连接超时时间（毫秒数）
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CommandCodecFactory("UTF-8")));

        KeepAliveMessageFactoryImpl kamfi = new KeepAliveMessageFactoryImpl();
        KeepAliveFilter kaf = new KeepAliveFilter(kamfi, IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
        /** 是否回发 */
        kaf.setForwardEvent(true);
        connector.getFilterChain().addLast("heart", kaf);

        connector.setHandler(new CalculatorClientHander());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress(IP, PORT));
        //等待建立连接
        connectFuture.awaitUninterruptibly();

        if(!connectFuture.isConnected()){
            log.debug("连接失败");
            return ;
        }
        log.debug("连接成功");

        IoSession session = connectFuture.getSession();

//        try {
//            Cmd1003 cmd1003 = (Cmd1003) CommandFactory.createCommand(CidConst.C1003);
//            cmd1003.getReqMsg().setCpu(0.3f);
//            cmd1003.getReqMsg().setDisk(0.24f);
//            cmd1003.getReqMsg().setMemory(0.41f);
//            session.write(cmd1003);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //关闭
        if (session != null) {
            if (session.isConnected()) {
                session.getCloseFuture().awaitUninterruptibly();
            }
            connector.dispose(true);
        }
    }
}
