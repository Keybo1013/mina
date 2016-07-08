package com.keybo.mina.server.protocol.filter;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author: xiaojian
 * @date: 16-6-22
 * @time: 下午3:17
 * @version: V1.0
 */
public class MyFilter extends IoFilterAdapter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) session.getRemoteAddress();
        log.debug("远程服务器地址：" + socketAddress.getAddress().getHostAddress());
        nextFilter.messageReceived(session, message);
    }
}
