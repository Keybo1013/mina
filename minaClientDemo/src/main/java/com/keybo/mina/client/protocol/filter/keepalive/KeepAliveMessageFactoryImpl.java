package com.keybo.mina.client.protocol.filter.keepalive;

import com.keybo.mina.client.protocol.cmd.Cmd1003;
import com.keybo.mina.client.protocol.comm.CidConst;
import com.keybo.mina.client.protocol.core.BaseCommand;
import com.keybo.mina.client.protocol.core.CommandFactory;
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
        return false;
    }

    @Override
    public boolean isResponse(IoSession session, Object message) {
        BaseCommand command = (BaseCommand) message;
        log.debug("判断是否心跳响应包");
        //判断是否心跳响应包
        if(command.getCid() == CidConst.C1003){
            Cmd1003 cmd1003 = (Cmd1003) command;
            if(cmd1003.getRespMsg() != null){
                log.debug("客户端接收心跳响应数据：" + cmd1003.getRespMsg().getSendTime() + "---" + cmd1003.getRespMsg().getRespCode());
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getRequest(IoSession session) {
        Cmd1003 cmd1003 = null;
        try {
            cmd1003 = (Cmd1003) CommandFactory.createCommand(CidConst.C1003);
            cmd1003.getReqMsg().setCpu(0.3f);
            cmd1003.getReqMsg().setDisk(0.24f);
            cmd1003.getReqMsg().setMemory(0.41f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cmd1003;
    }

    @Override
    public Object getResponse(IoSession session, Object request) {
        return null;
    }

}
