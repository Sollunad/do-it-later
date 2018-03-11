$(function(){
	
	multipleCards(cards);
	multipleGroups(groups);	
	var name = sessionStorage.getItem("userName");
	updateUsername(name);
	
	$.get("rest/GetGroups", { "uid" : name }, function(data){
			if (data) {
				multipleGroups(data);
			} else {
				alert("Gruppen holen fehlgeschlagen!");
			}
		});
	});
	
	$(".change_group").click(function(){
		var group = $(this).text();
		sessionStorage.setItem("activeGroup", group);
		updateActiveGroup(group);
		//TODO Get GroupID from GroupName
		$.get("rest/task/bygroup/" + groupId, function(data){
			if(data){
				multipleCards(data);
			}else{
				alert("Fehler beim laden der Tasks.")
			}
		});
	});
	
	
	$("#Create_Group").click(function(){
		var group_name = $("#Group_Name").val();
		$.post("rest/CreateGroup", {"uid" : name, "gname" : group_name}, function(data){
			alert(data);
		});
	});
	
	$("#Insert_User").click(function(){
		var group = sessionStorage.getItem("activeGroup");
		var user = $("#User_Name").val();
		$.post("rest/AddUser", {"gid" : group, "uname" : user}, function(data){
			alert(data);
		});
	});	

	$("#fixedButton").click(function(){
		$("input#Title").val("");
		$("input#Content").val("");
		$("input#Status").val("");
		$("input#Assignment").val("");
		$("#newCardModalConfirm").html("Update");
	});
	
	$("#newCardModalConfirm").click(fucntion(){
		var title = $("#title").val();
		var content = $("#content").val();
		var status = $("#status").val();
		var group = sessionStorage.getItem("activeGroup");
		//TODO Get GroupID from GroupName
		var assignment = $("#assignment").val();
		$.post("rest/task/" + title + "/" + content + "/" + status + "/" + groupId + "/" + assignment, function(data){
			alert(data);
		});
	});
	
	
});