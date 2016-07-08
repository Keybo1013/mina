package com.keybo.mina.server.protocol.codec;

import com.keybo.mina.server.protocol.comm.Constants;
import com.keybo.mina.server.protocol.core.BaseCommand;
import com.keybo.mina.server.protocol.core.CommandFactory;
import com.keybo.mina.server.protocol.utils.NumberUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 自定义解码器
 *
 * @author: xiaojian
 * @date: 16-6-30
 * @time: 下午4:14
 * @version: V1.0
 */
public class CommandDecoder extends CumulativeProtocolDecoder {

    private String charset;

    public CommandDecoder() {
        this.charset = "UTF-8";
    }

    public CommandDecoder(String charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if (in.prefixedDataAvailable(2)) {
            short length = in.getShort();//获取包长

            byte[] bytes = new byte[length];
            in.get(bytes);
            byte[] msgidBytes = new byte[Constants.COMMAND_LENGTH];
            System.arraycopy(bytes, 0, msgidBytes, 0, Constants.COMMAND_LENGTH);
            int msgid = NumberUtils.bytesToInt(msgidBytes);
            if (msgid != 0) {
                //通过工厂方法生成指定消息类型的指令对象
                BaseCommand command = CommandFactory.createCommand(msgid);

                byte[] cmdBodyBytes = new byte[length - Constants.COMMAND_LENGTH];
                System.arraycopy(bytes, Constants.COMMAND_LENGTH, cmdBodyBytes, 0, length - Constants.COMMAND_LENGTH);
                command.bodyFromBytes(cmdBodyBytes);
                out.write(command);
                return true;
            }
        }

        return false;
    }

}
