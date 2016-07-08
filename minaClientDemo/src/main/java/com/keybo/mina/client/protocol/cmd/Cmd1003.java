package com.keybo.mina.client.protocol.cmd;

import com.keybo.mina.client.protocol.comm.CidConst;
import com.keybo.mina.client.protocol.core.AbstractBaseCommand;
import com.keybo.mina.client.protocol.core.CommandRespMsg;
import com.keybo.mina.client.protocol.utils.GsonUtils;
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

    private ReqMsg reqMsg = new ReqMsg();
    private CommandRespMsg respMsg;

    @Override
    public short getCid() {
        return CidConst.C1003;
    }

    @Override
    public void bodyFromBytes(byte[] bytes) throws Exception {
        String json = new String(bytes, chartset);
        log.debug("Cmd1003客户端接收响应消息：" + json);
        respMsg = (CommandRespMsg) GsonUtils.fromJson(json, CommandRespMsg.class);
    }

    @Override
    public byte[] bodyToBytes() throws Exception {
        String json = GsonUtils.toJson(reqMsg);
        log.debug("Cmd1003客户端发送请求消息：" + json);
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
