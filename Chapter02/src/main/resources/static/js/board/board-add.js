document.querySelector("#addBtn").addEventListener("click", (event) => {
	event.preventDefault();
	
	const inputNode = document.createElement("input");
	inputNode.setAttribute("type", "file");
	inputNode.setAttribute("name", "boardAttach");
	inputNode.classList.add("form-control")
	
	const inputBtn = document.createElement("button");
	inputBtn.setAttribute("type", "button");
	inputBtn.appendChild(document.createTextNode("X"));
	inputBtn.classList.add("deleteBtn");
	
	const inputDiv = document.createElement("div");
	inputDiv.classList.add("mt-3");
	inputDiv.appendChild(inputNode);
	inputDiv.appendChild(inputBtn);
	
	document.querySelector("#addResult").appendChild(inputDiv);
	
});

document.querySelector("#addResult").addEventListener("click", (event) => {
	if (event.target.classList.contains("deleteBtn")) {
		event.target.parentElement.remove();
	}
});