<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Chat</title>
</head>

<body>
	<h1>Chat Page</h1>
	
	<div>
		<button onclick="connect()">Connect</button>
	</div>
	
	<div>
		<input type="text" id="msg" />
		<button id="send">SEND</button>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
	<script src="/js/chat/chat.js"></script>
</body>

</html>