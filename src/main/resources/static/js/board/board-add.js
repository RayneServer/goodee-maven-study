const addResult = document.querySelector("#addResult");
const deleteFile = document.querySelectorAll(".deleteFile");
let count = 5 - addResult.getAttribute("data-file-count");

document.querySelector("#addBtn").addEventListener("click", (event) => {
	event.preventDefault();
	
	if (addResult.childElementCount >= count) return;
	
	const inputNode = document.createElement("input");
	inputNode.setAttribute("type", "file");
	inputNode.setAttribute("name", "boardAttaches");
	inputNode.classList.add("form-control");
	inputNode.classList.add("col-6");
	
	const inputBtn = document.createElement("button");
	inputBtn.setAttribute("type", "button");
	inputBtn.classList.add("btn");
	inputBtn.classList.add("btn-danger");
	inputBtn.classList.add("ml-3");
	inputBtn.appendChild(document.createTextNode("X"));
	inputBtn.classList.add("deleteBtn");
	
	const inputDiv = document.createElement("div");
	inputDiv.classList.add("mt-3");
	inputDiv.classList.add("width-100");
	inputDiv.classList.add("d-flex");
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
		let deleteConfirm = false;
		
		Swal.fire({
		  title: "사진을 삭제하시겠습니까?",
		  text: "이 결정은 되돌릴 수 없습니다.",
		  icon: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "삭제",
			cancelButtonText: "취소"
		}).then((result) => {
		  if (result.isConfirmed) {
		    deleteConfirm = true;
		  }
			
			if (!deleteConfirm) return;
					
			const fileNum = file.getAttribute("data-file-num");
			
			const params = new URLSearchParams();
			params.append("fileNum", fileNum);
			
			fetch(`fileDelete`, {
				method : "POST",
				body : params
			})
			.then((data) => data.text())
			.then((data) => {
				if (data.trim() == 1) {
					Swal.fire({ text: "사진을 삭제했습니다!", icon: "success"});
					event.target.remove();
					count++;
				} else {
					Swal.fire({ text: "사진 삭제 중 오류가 발생했습니다!", icon: "warning"});
				}
			});
		});
	})
});


$('#boardContent').summernote({
  placeholder: '내용을 입력하세요.',
  tabsize: 2,
  height: 100,
  callbacks: {
	  onImageUpload: function(files) {
			const formData = new FormData();
			formData.append("boardFile", files[0]);
		  
	    fetch("boardFile", {
				method : "POST",
				body : formData
	    })
			.then((data) => data.text())
			.then((data) => {
				$('#boardContent').summernote("editor.insertImage", data);
			})
			.catch((e) => console.log(e))
			;
	  },
		onMediaDelete : function(files) {
			const fileName = $(files[0]).attr("src");
			
			const params = new URLSearchParams();
			params.append("fileName", fileName);
			
			fetch("boardFileDelete", {
				method : "POST",
				body : params
			})
			.then((data) => data.json())
			.then((data) => console.log(data));
		}
	}
});