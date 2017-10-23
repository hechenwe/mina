package com.sooncode.mina.client;
 
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
 
public class ClientMessageHandlerAdapter extends IoHandlerAdapter {
  
    
    public void messageReceived(IoSession session, Object message) throws Exception {
        String data = message.toString();
    }
    
    public void messageSent(IoSession session , Object message) throws Exception{
   
    	 
    }
    
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    	 
    }
}