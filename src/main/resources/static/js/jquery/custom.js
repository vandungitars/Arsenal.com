$(document).ready(function(){
	/*login-register*/
	$("#btn-login").click(function(){
		var formdata = $("#form-login").serializeArray();
		json = {};
		$.each(formdata,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/api/loginAdmin",
			type: "GET",
			data:{
				dataJson: JSON.stringify(json),
			},
			success: function(value){
				if(value == 'true'){
					nowLink = window.location.href;
					link = nowLink.replace("admin","admin/News");
					window.location = link;
				}
				else{
					alert("Login failed");
				}
			}
		})
	});
	$("#btn-register").click(function(){
		var formdata = $("#form-register").serializeArray();
		json = {};
		$.each(formdata,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/api/registerAdmin",
			type: "POST",
			data:{
				dataJson: JSON.stringify(json),
			},
			success: function(value){
				if(value == 'true'){
					nowLink = window.location.href;
					link = nowLink.replace("admin","admin/News");
					window.location = link;
				}
				else{
					alert("Register failed");
				}
			}
		})
	});
	
	/*News*/
	var files= [];
	$(".image").change(function(event){
		files = event.target.files;
		forms = new FormData();
		forms.append("file",files[0]);
		$.ajax({
			url: "/api/uploadfile",
			type: "POST",
			data:forms,
			contentType:false,
			processData:false,
			enctype:"multipart/form-data",
			success: function(value){}
		})
	});
	$("#addFile").click(function() {
        var fileIndex = $("#fileTable tr").children().length - 1;
        fileIndex = fileIndex+1;
        $("#fileTable").append('<tr><td>'
                                + '   <input name="files['+ fileIndex +']" type="file"/>'
                                + '</td></tr>');
    });
	$(".btn-addNews").click(function(){
		var optionText = $("#dropdownListType :selected").text();
		var formcontent = $("#form-cotentNews").serializeArray();
		json = {};
		$.each(formcontent, function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/admin/addNews",
			type: "POST",
			data: {
				dataJson: JSON.stringify(json),
				optionText: optionText,
			},
			success: function(value){}
		})
	});
	$(".btn-updateNews").click(function(){
		var idNews = $(this).closest("tr").find(".idNews").text();
		$(".btn-confirmUpdateNews").click(function(){
			var formupdateCotentNews = $("#form-updateCotentNews").serializeArray();
			json = {};
			$.each(formupdateCotentNews,function(i,field){
				json[field.name] = field.value;
			});
			$.ajax({
				url: "/admin/updateNews",
				type: "POST",
				data: {
					dataJson: JSON.stringify(json),
					idNews: idNews,
				},
				success: function(value){
					alert("hello");
				}
			})
		});
	});
	$(".btn-deleteNews").click(function(){
		var idNews = $(this).closest("tr").find(".idNews").text();
		alert(idNews);
		$(this).closest("tr").remove();
		$.ajax({
			url: "/admin/deleteNews",
			type: "POST",
			data: {
				idNews: idNews,
			},
			success: function(value){}
		})
	});
	
	/*add database PlayerProfile*/
	$(".btn-AddPlayerProfile").click(function(){
		var formadd = $("#form-addPlayerProfile").serializeArray();
		json = {};
		$.each(formadd, function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/admin/addPlayerProfile",
			type: "POST",
			data: {
				dataJson: JSON.stringify(json),
			},
			success: function(value){}
		})
	});
	$(".btn-updatePlayerProfile").click(function(){
		var idPlayer = $(this).closest("tr").find(".idPlayerProfile").text();
		$(".btn-confirmUpdatePlayerProfile").click(function(){
			var formupdatePlayerProfile = $("#form-updatePlayerProfile").serializeArray();
			json = {};
			$.each(formupdatePlayerProfile,function(i,field){
				json[field.name] = field.value;
			});
			$.ajax({
				url: "/admin/updatePlayerProfile",
				type: "POST",
				data: {
					dataJson: JSON.stringify(json),
					idPlayer: idPlayer,
				},
				success: function(value){}
			})
		});
	});
	$(".btn-deletePlayerProfile").click(function(){
		var idPlayer = $(this).closest("tr").find(".idPlayerProfile").text();
		$(this).closest("tr").remove();
		$.ajax({
			url: "/admin/deletePlayerProfile",
			type: "POST",
			data: {
				idPlayer: idPlayer,
			},
			success: function(value){}
		})
	});
	
	/*Product*/	
	$(".btn-addProduct").click(function(){
	    var optionText = $("#dropdownListAddProduct :selected").text();
		var formAddProduct = $("#form-addProduct").serializeArray();
		json = {};
		$.each(formAddProduct,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/admin/addProduct",
			type: "POST",
			data: {
				dataJson : JSON.stringify(json),
				optionText: optionText,
			},
			success: function(value){}
		})
	});
	
	$(".btn-updateProduct").click(function(){
		var idProduct = $(this).closest("tr").find(".idProduct").text();
		$(".btn-confirmUpdateProduct").click(function(){
			var optionText = $("#dropdownListUpdateProduct :selected").text();
			var formUpdateProduct = $("#form-updateProduct").serializeArray();
			json = {};
			$.each(formUpdateProduct,function(i,field){
				json[field.name] = field.value;
			});
			$.ajax({
				url: "/admin/updateProduct",
				type: "POST",
				data:{
					dataJson: JSON.stringify(json),
					idProduct: idProduct,
					optionText: optionText,
				},
				success: function(value){}
			})
		});
	});
	$(".btn-deleteProduct").click(function(){
		var idProduct = $(this).closest("tr").find(".idProduct").text();
		$(this).closest("tr").remove();
		$.ajax({
			url: "/admin/deleteProduct",
			type: "POST",
			data: {
				idProduct: idProduct,
			},
			success: function(value){}
		})
	});
		
	/*Schedule*/
	$(".btn-addSchedule").click(function(){
		var datetime = $(".datetimepicker").val();
		var formAddSchedule = $("#form-AddSchedule").serializeArray();
		json = {};
		$.each(formAddSchedule,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/admin/addSchedule",
			type: "POST",
			data:{
				dataJson: JSON.stringify(json),
				datetime: datetime,
			},
			success: function(value){}
		})
	});
	$(".btn-updateSchedule").click(function(){
		var idSchedule = $(this).closest("tr").find(".idSchedule").text();
		$(".btn-confirmUpdateSchedule").click(function(){
			var datetime = $(".datetimepicker").val();
			var formUpdateSchedule = $("#form-updateSchedule").serializeArray();
			json = {};
			$.each(formUpdateSchedule,function(i,field){
				json[field.name] = field.value;
			});
			$.ajax({
				url: "/admin/updateSchedule",
				type: "POST",
				data:{
					dataJson: JSON.stringify(json),
					idSchedule: idSchedule,
					datetime: datetime,
				},
				success: function(value){}
			})
		});
	});
	$(".btn-deleteSchedule").click(function(){
		var idSchedule = $(this).closest("tr").find(".idSchedule").text();
		$(this).closest("tr").remove();
		$.ajax({
			url: "/admin/deleteSchedule",
			type: "POST",
			data: {
				idSchedule: idSchedule,
			},
			success: function(value){}
		})
	});
	
	/*video*/
	var files= [];
	$(".video").change(function(event){
		files = event.target.files;
		forms = new FormData();
		forms.append("file",files[0]);
		$.ajax({
			url: "/api/uploadfile",
			type: "POST",
			data:forms,
			contentType:false,
			processData:false,
			enctype:"multipart/form-data",
			success: function(value){}
		})
	});
	$(".btn-addVideo").click(function(){
		var formVideo = $("#form-addVideo").serializeArray();
		json = {};
		$.each(formVideo, function(i,field){
			json[field.name] = field.value;
		})
		$.ajax({
			url: "/admin/addVideo",
			type: "POST",
			data:{
				dataJson: JSON.stringify(json),
			},
			success: function(value){}
		})
	});
	$(".btn-updateVideo").click(function(){
		var idVideo = $(this).closest("tr").find(".idVideo").text();
		$(".btn-confirmUpdateVideo").click(function(){
			var formVideo = $("#form-updateVideo").serializeArray();
			json = {};
			$.each(formVideo, function(i,field){
				json[field.name] = field.value;
			})
			$.ajax({
				url: "/admin/updateVideo",
				type: "POST",
				data:{
					dataJson: JSON.stringify(json),
					idVideo: idVideo,
				},
				success: function(value){}
			})
		});
	});
	$(".btn-deleteVideo").click(function(){
		var idVideo = $(this).closest("tr").find(".idVideo").text();
		$(this).closest("tr").remove();
		$.ajax({
			url: "/admin/deleteVideo",
			type: "POST",
			data: {
				idVideo: idVideo,
			},
			success: function(value){}
		})
	});
	/*comment*/
	$(".btn-comment").click(function(){
		var formComment = $(".form-cotentNews").serializeArray();
		json = {};
		$.each(formComment,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/api/comment",
			type: "POST",
			data:{
				dataJson: JSON.stringify(json),
			},
			success: function(value){}
		})
	})
	/*product*/
	$(".btn-confirmCustomer").click(function(){
		var formCustomer = $(".form-inforcustomer").serializeArray();
		json = {};
		$.each(formCustomer, function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/api/customer",
			type: "POST",
			data:{
				dataJson : JSON.stringify(json),
			},
			success: function(value){}
		})
	});
})
