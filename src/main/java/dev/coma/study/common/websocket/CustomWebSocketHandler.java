package dev.coma.study.common.websocket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomWebSocketHandler implements WebSocketHandler {
	private List<WebSocketSession> userList = new ArrayList<>();
	private Map<String, WebSocketSession> userMap = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// WebSocket으로 연결되었을 때 실행
		Principal principal = session.getPrincipal();
		
		log.info("Connected: {}", session.getId());
		userMap.put(principal.getName(), session);
		userList.add(session);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 사용자가 메시지 전송 시 실행
		log.info("{}", message.getPayload());
		
		for (WebSocketSession user : userList) {
			user.sendMessage(message);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		log.info("Disconnected: {}", session.getId());
		userList.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}

}
