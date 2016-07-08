package com.keybo.mina.client.protocol.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 上午9:52
 * @version: V1.0
 */
public class CommandFactory {

    private static String CMD_BASE_PACKAGE = "com.keybo.mina.client.protocol.cmd.";
    private static String BIZ_BASE_PACKAGE = "com.keybo.mina.client.protocol.biz.";

    public final static String CMD_HEAD = "Cmd";
    public final static String BIZ_HEAD = "Biz";

    /**
     * 缓存存在的class信息
     */
    private static final Map<String, Class<?>> clazz = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 缓存不存在的class信息
     */
    private static final Map<String, Boolean> clazz_null = new ConcurrentHashMap<String, Boolean>();

    /**
     * 获取package
     *
     * @param head
     * @return
     */
    private static String getBasePackage(String head) {
        if (head.equalsIgnoreCase(CMD_HEAD)) {
            return CMD_BASE_PACKAGE;
        } else if (head.equalsIgnoreCase(BIZ_HEAD)) {
            return BIZ_BASE_PACKAGE;
        }
        return "";
    }

    /**
     * 按规则 组装全局 类名
     *
     * @param head
     * @param commandCode
     * @return
     */
    private static String generateSubPackage(String head, int commandCode) {
        return head + commandCode;
    }

    /**
     * 生成指定消息类型的业务处理对象
     *
     * @param msgid
     * @return
     * @throws Exception
     */
    public static Biz createBiz(int msgid) throws Exception {
        Class<?> c = getClazz(BIZ_HEAD, msgid);
        if (c == null)
            return null;
        else
            return (Biz) c.newInstance();
    }

    /**
     * 生成指定消息类型的指令对象
     *
     * @param msgid
     * @return
     * @throws Exception
     */
    public static BaseCommand createCommand(int msgid) throws Exception {
        Class<?> c = getClazz(CMD_HEAD, msgid);
        if (c == null)
            return null;
        else
            return (BaseCommand) c.newInstance();
    }

    protected static Class<?> getClazz(String head, int id) {
        String key = head + id;
        Class<?> c = clazz.get(key);
        if (c == null) {
            if (clazz_null.get(key) != null)
                return null;
            try {
                c = Class.forName(getBasePackage(head) + generateSubPackage(head, id));
            } catch (ClassNotFoundException e) {
                clazz_null.put(key, true);
                return null;
            }
            clazz.put(key, c);
        }
        return c;
    }

    /**
     * 将10进制报文ID转成16进制
     *
     * @param msgid 报文ID
     * @return
     */
    protected static short getMID(short msgid) {
        try {
            return Short.parseShort(Integer.toHexString(msgid));
        } catch (NumberFormatException nfe) {
        }
        return 0;
    }

}
