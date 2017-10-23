package com.sooncode.mina.service;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
 
 
 
 
public class ServerMessageHandler implements IoHandler {
    
   
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("服务器发生异常： {}"+ cause.getMessage());
    }
 
   
    public void messageReceived(IoSession session, Object message) throws Exception {
    	System.out.println("服务器接收到数据： {}"+ message);
        IoSessionManager.putIoSession( message.toString(), session);
    }
 
   
    public void messageSent(IoSession session, Object message) throws Exception {
    	System.out.println("服务器发送消息： {}"+ message);
    }
 
    
    public void sessionClosed(IoSession session) throws Exception {
    	System.out.println("关闭当前session：{}#{}"+ session.getId()+ session.getRemoteAddress());
        
        CloseFuture closeFuture = session.close(true);
        closeFuture.addListener(new IoFutureListener<IoFuture>() {
            public void operationComplete(IoFuture future) {
                if (future instanceof CloseFuture) {
                    ((CloseFuture) future).setClosed();
                    System.out.println("sessionClosed CloseFuture setClosed-->{},"+ future.getSession().getId());
                }
            }
        });
    }
 
    
    public void sessionCreated(IoSession session) throws Exception {
    	System.out.println("创建一个新连接： "+ session.getRemoteAddress() + " : " + session.getId());
        //session.write("welcome to the chat room !");
    }
 
   
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    	System.out.println("当前连接{}处于空闲状态：{}"+ session.getRemoteAddress()+ status);
    }
 
 
    public void sessionOpened(IoSession session) throws Exception {
    	System.out.println("打开一个session：{}#{}"+ session.getId()+ session.getBothIdleCount());
    }
}
 