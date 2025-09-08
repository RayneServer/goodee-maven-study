package dev.coma.study.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/api/**")
public class ChatController {

	@GetMapping("/chat")
	public String room() {
		return "chat/chat";
	}
	
	@MessageMapping("/chat/send")
	@SendTo("/topic/messages")
	public String sendMessage(String message) {
		System.out.println(message);
		return message;
	}
	
	
	
//	@GetMapping("chat")
//	public String getChat() {
//		return "chat/chat";
//	}
}
