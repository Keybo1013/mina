package com.keybo.mina.client.protocol.core;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IoSession缓存类
 *
 * @author: xiaojian
 * @date: 16-7-5
 * @time: 下午4:29
 * @version: V1.0
 */
public class SessionMap {

    private static Logger log = LoggerFactory.getLogger(SessionMap.class);

    private static SessionMap instance = null;

    private Map<String, IoSession> map = new ConcurrentHashMap<String, IoSession>();

    public static SessionMap getInstance() {
        if (instance == null) {
            instance = new SessionMap();
        }
        return instance;
    }

    /**
     * 保存session会话
     *
     * @param key
     * @param session
     */
    public void addSession(String key, IoSession session) {
        log.debug("保存会话到SessionMap单例---key=" + key);
        this.map.put(key, session);
    }

    /**
     * 根据key查找缓存的session
     *
     * @param key
     * @return
     */
    public IoSession getSession(String key) {
        log.debug("获取会话从SessionMap单例---key=" + key);
        return this.map.get(key);
    }

    /**
     * 根据key查找缓存的session
     *
     * @param key
     * @return
     */
    public void removeSession(String key) {
        log.debug("删除会话从SessionMap单例---key=" + key);
        this.map.remove(key);
    }

    /**
     * 发送消息到客户端
     *
     * @param key
     * @param message
     */
    public void sendMessage(String key, Object message) {
        IoSession session = getSession(key);

        log.debug("反向发送消息到客户端Session---key=" + key + "----------消息=" + message);
        if (session == null) {
            return ;
        }
        session.write(message);
    }

    /**
     * 发送消息到客户端
     *
     * @param keys
     * @param message
     */
    public void sendMessage(String[] keys, Object message) {
        for (String key : keys) {
            sendMessage(key, message);
        }
    }

}
