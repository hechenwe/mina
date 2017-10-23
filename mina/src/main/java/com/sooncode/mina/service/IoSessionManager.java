package com.sooncode.mina.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class IoSessionManager {
	private static Map<String, IoSession> ioSessions = new HashMap<String, IoSession> ();

	
	public static void putIoSession(String key , IoSession ioSession){
		ioSessions.put(key, ioSession);
	}
	
	public static IoSession getIoSession (String key){
		return ioSessions.get(key);
	}
}
