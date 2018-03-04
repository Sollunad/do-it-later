$().ready(function(){
	var uname = $("#usr_name").val();
	var gid = 5;
	$("#usr_confirm").click(function(){
		var url = "http://localhost:8080/DoItLater/AddUser?uname="+uname+"&gid="+gid;
		$.post(url, function(data){
			window.alert(data);
		});	
	});
	
	$("#add_group").click(function(){
		var name = $("#group_name").val();
		var url = "http://localhost:8080/DoItLater/CreateGroup?gname="+name+"&uid="+uid;
		$.post(url, function(data){
			window.alert(data);
			$("#group_name").val("");
		});
	});
	
	var uid = sessionStorage.getItem("userID");
	var url = "http://localhost:8080/DoItLater/GetGroups?uid="+uid;
	$.get(url, function(data){
		$("#Group_List").append(
				$("<li></li>").html(
					$("<a></a>")
						.attr("href", "")
						.html(data)
					)
				);
		});
});				