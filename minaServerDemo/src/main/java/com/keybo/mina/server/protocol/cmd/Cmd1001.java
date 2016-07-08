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
public class Cmd1001 extends AbstractBaseCommand {

    private static Logger log = LoggerFactory.getLogger(Cmd1001.class);

    private ReqMsg reqMsg;
    private CommandRespMsg respMsg = new CommandRespMsg();

    @Override
    public short getCid() {
        return CidConst.C1001;
    }

    @Override
    public void bodyFromBytes(byte[] bytes) throws Exception {
        String json = new String(bytes, chartset);
        log.debug("Cmd1001服务端接收请求消息：" + json);
        reqMsg = (ReqMsg) GsonUtils.fromJson(json, ReqMsg.class);
    }

    @Override
    public byte[] bodyToBytes() throws Exception {
        String json = GsonUtils.toJson(respMsg);
        log.debug("Cmd1001服务端发送响应消息：" + json);
        return json.getBytes(chartset);
    }

    public class ReqMsg implements Serializable {
        /**
         * ip
         */
        private String ip;
        /**
         * 终端名字
         */
        private String name;
        /**
         * 终端mac
         */
        private String mac;
        /**
         * 屏幕编号(GUID,保证唯一)
         */
        private String guid;
        /**
         * 版本
         */
        private String version;
        /**
         * 版本
         */
        private String resolution;
        /**
         * 色深(有多少字节表示的色深，比如32表示32位色深)
         */
        private String colorDepth;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getColorDepth() {
            return colorDepth;
        }

        public void setColorDepth(String colorDepth) {
            this.colorDepth = colorDepth;
        }
    }

    public ReqMsg getReqMsg() {
        return reqMsg;
    }

    public CommandRespMsg getRespMsg() {
        return respMsg;
    }
}
