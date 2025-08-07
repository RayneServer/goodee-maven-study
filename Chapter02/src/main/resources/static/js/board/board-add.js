const addResult = document.querySelector("#addResult");
const deleteFile = document.querySelectorAll(".deleteFile");

document.querySelector("#addBtn").addEventListener("click", (event) => {
	event.preventDefault();
	
	const count = 5 - addResult.getAttribute("data-file-count");
	
	if (addResult.childElementCount >= count) return;
	
	const inputNode = document.createElement("input");
	inputNode.setAttribute("type", "file");
	inputNode.setAttribute("name", "boardAttaches");
	inputNode.classList.add("form-control")
	
	const inputBtn = document.createElement("button");
	inputBtn.setAttribute("type", "button");
	inputBtn.appendChild(document.createTextNode("X"));
	inputBtn.classList.add("deleteBtn");
	
	const inputDiv = document.createElement("div");
	inputDiv.classList.add("mt-3");
	inputDiv.appendChild(inputNode);
	inputDiv.appendChild(inputBtn);
	
	addResult.appendChild(inputDiv);
	
});

addResult.addEventListener("click", (event) => {
	if (event.target.classList.contains("deleteBtn")) {
		event.target.parentElement.remove();
	}
});

deleteFile.forEach((file) => {
	file.addEventListener("click", (event) => {
		const fileNum = file.getAttribute("data-file-num");
		
		const params = new URLSearchParams();
		params.append("fileNum", fileNum);
		
		fetch(`fileDelete`, {
			method : "POST",
			body : params
		})
		.then((data) => data.text())
		.then((data) => console.log(data))
	})
})