package com.keybo.mina.server.protocol.cmd;

import com.keybo.mina.server.protocol.comm.CidConst;
import com.keybo.mina.server.protocol.core.AbstractBaseCommand;
import com.keybo.mina.server.protocol.core.CommandRespMsg;
import com.keybo.mina.server.protocol.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午5:19
 * @version: V1.0
 */
public class Cmd1003 extends AbstractBaseCommand {

    private static Logger log = LoggerFactory.getLogger(Cmd1003.class);

    private ReqMsg reqMsg;
    private CommandRespMsg respMsg = new CommandRespMsg();

    @Override
    public short getCid() {
        return CidConst.C1003;
    }

    @Override
    public void bodyFromBytes(byte[] bytes) throws Exception {
        String json = new String(bytes, chartset);
        log.debug("服务端接收请求消息：" + json);
        reqMsg = (ReqMsg) GsonUtils.fromJson(json, ReqMsg.class);
    }

    @Override
    public byte[] bodyToBytes() throws Exception {
        String json = GsonUtils.toJson(respMsg);
        log.debug("服务端发送响应消息：" + json);
        return json.getBytes(chartset);
    }

    public class ReqMsg implements Serializable {
        /**
         * cpu占比
         */
        private float cpu;
        /**
         * 磁盘占比
         */
        private float disk;
        /**
         * 内存占比
         */
        private float memory;

        public float getCpu() {
            return cpu;
        }

        public void setCpu(float cpu) {
            this.cpu = cpu;
        }

        public float getDisk() {
            return disk;
        }

        public void setDisk(float disk) {
            this.disk = disk;
        }

        public float getMemory() {
            return memory;
        }

        public void setMemory(float memory) {
            this.memory = memory;
        }
    }

    public ReqMsg getReqMsg() {
        return reqMsg;
    }

    public CommandRespMsg getRespMsg() {
        return respMsg;
    }
}
