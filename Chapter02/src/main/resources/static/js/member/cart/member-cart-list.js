let checkAll = document.querySelector("#checkAll");
const productCheckBoxes = document.querySelectorAll(".cartCheck");

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