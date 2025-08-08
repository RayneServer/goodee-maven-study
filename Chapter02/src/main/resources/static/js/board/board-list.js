const searchForm = document.querySelector("#searchForm");
const pageNum = document.querySelector("#pageNum");

document.querySelectorAll(".page-num").forEach((button) => {
	button.addEventListener("click", (event) => {
		event.preventDefault();
		
		const data = event.target.getAttribute("data-page-num");
		pageNum.value = data;
		
		searchForm.submit();
	});
});