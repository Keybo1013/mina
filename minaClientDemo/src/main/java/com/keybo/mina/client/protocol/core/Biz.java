package com.keybo.mina.client.protocol.core;

import org.apache.mina.core.session.IoSession;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午2:17
 * @version: V1.0
 */
public interface Biz {

    public void doBiz(IoSession session, BaseCommand command) throws Exception;

}
