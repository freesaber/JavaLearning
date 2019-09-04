package cn.freesaber.sell.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {
    private final Logger logger = LoggerFactory.getLogger(WebSocket.class);
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        logger.info("【websocket消息】有新的连接,总数:{}", webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        logger.info("【websocket消息】连接断开,总数:{}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("【websocket消息】收到客户端发来的消息", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            logger.info("【websocket消息】广播消息", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
