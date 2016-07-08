package com.keybo.mina.client.protocol.codec;

import com.keybo.mina.client.protocol.core.BaseCommand;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 自定义编码器
 *
 * @author: xiaojian
 * @date: 16-6-30
 * @time: 下午3:49
 * @version: V1.0
 */
public class CommandEncoder extends ProtocolEncoderAdapter {

    private String charset;

    public CommandEncoder() {
        this.charset = "UTF-8";
    }

    public CommandEncoder(String charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        BaseCommand command = (BaseCommand) message;
        byte[] bytes = command.toBytes();
        IoBuffer buf = IoBuffer.allocate(bytes.length, false);

        buf.setAutoExpand(true);
        buf.putShort( (short) bytes.length );
        buf.put(bytes);

        buf.flip();
        out.write(buf);
    }

}
