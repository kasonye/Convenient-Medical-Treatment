package com.yks.cmt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint(value = "/websocket/{userName}")
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();



    //存放当前用户名
    private String userName;



    //存放在线的用户数量
    private static Integer userNumber = 0;

    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        webSocketSet.add(this);

        this.session.getAsyncRemote().sendText("欢迎进入便捷就医咨询室，当前在线人数:"+webSocketSet.size());
    }

    @OnClose
    public void OnClose(){
        webSocketSet.remove(this);
        System.out.println("有一连接断开，当前在线人数："+webSocketSet.size());
    }

    @OnMessage
    public void OnMessage(String message, Session session,@PathParam("userName") String userName){
        System.out.println(userName +":"+message);
        this.session = session;
        this.userName = userName;
        broadcast(userName+":"+message);
    }

    @OnError
    public void OnError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void broadcast(String message){
        for (WebSocket socket: webSocketSet
             ) {
            socket.session.getAsyncRemote().sendText(message);
        }
    }


}
