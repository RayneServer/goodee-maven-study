const frm = document.querySelector("#frm");

document.querySelectorAll(".action").forEach((action) => {
	action.addEventListener("click", () => {
		const kind = action.getAttribute("data-kind");

		if (kind == 'u') {
			frm.setAttribute("action", "./update");
			frm.submit();
			return;
		}
		
		if (kind == 'd') {
			frm.setAttribute("action", "./delete");
			frm.setAttribute("method", "POST");
			frm.submit();
			return;
		}
	});
});