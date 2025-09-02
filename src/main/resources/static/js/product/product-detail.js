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
		
		if (kind == 'b') {
			const productNum = document.querySelector("#productNum").getAttribute("value");
			
			frm.setAttribute("action", "/account/add?productNum=" + productNum);
			frm.setAttribute("method", "GET");
			frm.submit();
			return;
		}
		
		if (kind == 'a') {
			const productNum = document.querySelector("#productNum").getAttribute("value");
			
			fetch("/member/cart/add?productNum=" + productNum, {
				method : "GET"
			})
			.then((data) => data.text())
			.then((data) => {
				if (data == "false") {
					Swal.fire({ icon: "error", text: "장바구니에 담는 중 오류가 발생했습니다." });
				} else {
					Swal.fire({
						title: "장바구니에 추가했습니다!",
					  text: "내 장바구니로 이동하시겠습니까?",
					  icon: "success",
					  showCancelButton: true,
					  confirmButtonColor: "#3085d6",
					  confirmButtonText: "확인",
						cancelButtonColor: "#d33",
						cancelButtonText: "취소"
					}).then((result) => {
					  if (result.isConfirmed) {
						  location.href = "/member/cart/list";
					  }
					});
				}
			});
			
			return;
		}
	});
});