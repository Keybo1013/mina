package com.keybo.mina.client.protocol.biz;

import com.keybo.mina.client.protocol.cmd.Cmd1001;
import com.keybo.mina.client.protocol.core.AbstractBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端响应注册消息业务处理类
 *
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午2:33
 * @version: V1.0
 */
public class Biz1001 extends AbstractBiz {

    private static Logger log = LoggerFactory.getLogger(Biz1001.class);

    @Override
    protected void exec() throws Exception {
        Cmd1001 cmd1001 = (Cmd1001) command;
        log.debug(cmd1001.getRespMsg().getSendTime() + "== " + cmd1001.getRespMsg().getRespCode());
    }

}
