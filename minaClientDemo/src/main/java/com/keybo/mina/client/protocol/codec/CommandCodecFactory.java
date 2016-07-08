package com.keybo.mina.client.protocol.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 上午11:27
 * @version: V1.0
 */
public class CommandCodecFactory implements ProtocolCodecFactory {

    /**
     * 编码器
     */
    private CommandEncoder encoder;
    /**
     * 解码器
     */
    private CommandDecoder decoder;

    public CommandCodecFactory(String charset) {
        encoder = new CommandEncoder(charset);
        decoder = new CommandDecoder(charset);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
