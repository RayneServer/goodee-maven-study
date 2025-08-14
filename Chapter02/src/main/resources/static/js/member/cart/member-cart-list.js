let checkAll = document.querySelector("#checkAll");
let productCheckBoxes = document.querySelectorAll(".cartCheck");

checkAll.addEventListener("change", () => {
	checkAll = document.querySelector("#checkAll");
	
	if (checkAll.checked) {
		productCheckBoxes.forEach((checkBox) => {
			checkBox.checked = true;
		});
	} else {
		productCheckBoxes.forEach((checkBox) => {
			checkBox.checked = false;
		});
	}
});

productCheckBoxes.forEach((checkBox) => {
	checkBox.addEventListener("change", () => {
		let count = true;
		
		for (check of productCheckBoxes) {
			if (!check.checked) {
				count = false;
				break;
			}
		}
		
		if (count) checkAll.checked = true;
		else checkAll.checked = false;
	})
});

document.querySelector("#deleteProduct").addEventListener("click", (event) => {
	event.preventDefault();
	
	const cartForm = document.querySelector("#cartFrm");
	cartForm.setAttribute("method", "POST");
	cartForm.setAttribute("action", "./delete")
	cartForm.submit();
})

document.querySelector("#buyProduct").addEventListener("click", (event) => {
	event.preventDefault();
	
	const cartForm = document.querySelector("#cartFrm");
		cartForm.setAttribute("method", "POST");
		cartForm.setAttribute("action", "/account/add");
		cartForm.submit();
	
})