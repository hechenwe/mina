package com.sooncode.mina.client;

 
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.sooncode.mina.client.charset.CharsetCodecFactory;
 
 
 
public class MinaClient {
    
    private static final String IP = "127.0.0.1";
    private static final int PORT = 3456;
    private static IoSession session;
    public static void start() {
 
        // 创建一个socket连接
    	SocketConnector connector = new NioSocketConnector();
        // 设置链接超时时间
        connector.setConnectTimeoutMillis(3000);
        // 获取过滤器链
        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        // 添加编码过滤器 处理乱码、编码问题
        filterChain.addLast("codec", new ProtocolCodecFilter(new CharsetCodecFactory()));
 
        // 消息核心处理器
        connector.setHandler(new ClientMessageHandlerAdapter());
 
        // 连接服务器，知道端口、地址
        ConnectFuture future = connector.connect(new InetSocketAddress(IP,PORT));
        // 等待连接创建完成
        future.awaitUninterruptibly();
        // 获取当前session
        session = future.getSession();
        
    }
  
 
    public static void send(String message) {
        session.write(message + "\n");
    }
  
    
    public static void main(String[] args) {
		start();
		send("hello");
	}
     
}