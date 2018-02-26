$().ready(function(){
	$("#usr_confirm").click(function(){
		$.post("/Add_User", function(data){
			alert("Abgeschickt " + data);
		});
	});
});