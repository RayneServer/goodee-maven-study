// const socket = new WebSocket("ws://192.168.1.74/chat")
const inputMsg = document.querySelector("#msg")
// const send = document.querySelector("#send")
const socket = new SockJS("/ws")
const stompClient = Stomp.over(socket)

stompClient.subscribe("/topic/messages", (message) => {
	const chat = JSON.parse(message.body)
	console.log(chat)
})
		
/*socket.addEventListener("open", () => {
	console.log("Socket 연결 성공!")
})

socket.addEventListener("message", (event) => {
	console.log("Message 수신: " + event.data)
})

socket.addEventListener("close", () => {
	console.log("Socket 연결 해제...")
})

socket.addEventListener("error", () => {
	console.log("Error 발생!!")
})

send.addEventListener("click", () => {
	let message = inputMsg.value
	socket.send(message)
})*/

function connect() {
	stompClient.send("/app/chat/send", {}, inputMsg)
}