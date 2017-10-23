package com.sooncode.mina.service;

import org.apache.mina.core.session.IoSession;

public class MessageSendManager {
	public void send(IoSession ioSession, String message) {
		ioSession.write(message + "\n");
	}
}
