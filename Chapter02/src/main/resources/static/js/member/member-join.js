
document.querySelector("#profileBtn").addEventListener("click", (event) => {
	event.preventDefault();

	const profileBlock = document.querySelector("#profileBlock");
	
	if (profileBlock.hasChildNodes()) {
		profileBlock.firstChild.remove();
	} else {
		const inputNode = document.createElement("input");
		inputNode.setAttribute("type", "file");
		inputNode.setAttribute("name", "memberProfile");
		inputNode.classList.add("form-control");
		
		profileBlock.appendChild(inputNode);
	}
});