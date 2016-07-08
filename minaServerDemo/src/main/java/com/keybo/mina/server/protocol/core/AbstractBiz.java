package com.keybo.mina.server.protocol.core;

import org.apache.mina.core.session.IoSession;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午2:18
 * @version: V1.0
 */
public abstract class AbstractBiz implements Biz {

    protected IoSession session;
    protected BaseCommand command;

    protected abstract void exec() throws Exception;

    @Override
    public void doBiz(IoSession session, BaseCommand command) throws Exception {
        before(); //处理之前
        try {
            this.session = session;
            this.command = command;
            exec();
        } finally {
            after(); //处理之后
        }
    }

    public void before() {

    }

    public void after() {

    }
}
