$().ready(function(){
	$("#usr_confirm").click(function(){
		var name = $("#usr_name").val();
		$.post("http://localhost:8080/DoItLater/Add_User?usr_name=" + name, function(data){
			alert(data);
		});
	});
});