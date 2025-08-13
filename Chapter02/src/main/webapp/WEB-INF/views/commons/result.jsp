<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title></title>
	
	<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.22.2/dist/sweetalert2.min.css" rel="stylesheet">
</head>

<body>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.22.2/dist/sweetalert2.all.min.js"></script>
	<script>
		let icon = '${ resultIcon }';
		if (icon == null || icon == "") icon = "success";
		
		const url = '${ url }';
	
		Swal.fire({
		  text: '${ resultMsg }',
		  icon: icon,
		  showCancelButton: false,
		  confirmButtonColor: "#3085d6",
		  confirmButtonText: "확인"
		}).then((result) => {
		  if (result.isConfirmed) {
			  if (url != null && url != "") location.href = url;
			  else history.back();
		  }
		});
	</script>
</body>

</html>