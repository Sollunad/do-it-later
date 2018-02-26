$().ready(function(){
	$("#usr_confirm").click(function(){
		$.post("http://localhost:8080/DoItLater/Add_User", function(data){
			alert(data);
		});
	});
});