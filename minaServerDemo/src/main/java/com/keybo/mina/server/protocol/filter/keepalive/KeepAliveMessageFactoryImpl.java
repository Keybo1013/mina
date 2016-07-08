package com.keybo.mina.server.protocol.filter.keepalive;

import com.keybo.mina.server.protocol.cmd.Cmd1003;
import com.keybo.mina.server.protocol.comm.CidConst;
import com.keybo.mina.server.protocol.comm.RespCodeConstants;
import com.keybo.mina.server.protocol.core.BaseCommand;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: xiaojian
 * @date: 16-7-6
 * @time: 上午11:24
 * @version: V1.0
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    private static Logger log = LoggerFactory.getLogger(KeepAliveMessageFactoryImpl.class);

    @Override
    public boolean isRequest(IoSession session, Object message) {
        BaseCommand command = (BaseCommand) message;
        if(command.getCid() == CidConst.C1003){
            return true;
        }
        return false;
    }

    @Override
    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    @Override
    public Object getRequest(IoSession session) {
        return null;
    }

    @Override
    public Object getResponse(IoSession session, Object request) {
        Cmd1003 cmd1003 = (Cmd1003) request;
        log.debug("服务端接收心跳请求数据：" + cmd1003.getReqMsg().getCpu() + "---" + cmd1003.getReqMsg().getDisk() + "---" + cmd1003.getReqMsg().getMemory());

        cmd1003.getRespMsg().setRespCode(RespCodeConstants.SUCCESS);

        return cmd1003;
    }

}
