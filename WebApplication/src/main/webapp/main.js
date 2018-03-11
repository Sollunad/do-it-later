$(function(){
	
	//multipleCards(cards);
	//multipleGroups(groups);	
	var name = $.cookie("userName"); // sessionStorage.getItem("userName");
	console.log(name);
	updateUsername(name);
	
	$.get("rest/GetGroups/" + name, (data) => {
			if (data) {
				multipleGroups(data);
			} else {
				alert("Gruppen holen fehlgeschlagen!");
			}
	});
	
	$(".change_group").click(() => {
		var group = $(this).text();
		sessionStorage.setItem("activeGroup", group);
		updateActiveGroup(group);
		// TODO Get GroupID from GroupName
		$.get("rest/task/bygroup/" + groupId, function(data){
			if(data){
				multipleCards(data);
			}else{
				alert("Fehler beim laden der Tasks.")
			}
		});
	});
	
	
	$("#Create_Group").click(() => {
		var group_name = $("#Group_Name").val();
		console.log("Neue Gruppe: " + group_name + ", Admin: " + name);
		$.post("rest/CreateGroup", {"name" : group_name, "admin" : name}, function(data){
			alert(data);
		});
	});
	
	$("#Insert_User").click(() => {
		var group = sessionStorage.getItem("activeGroup");
		var user = $("#User_Name").val();
		$.post("rest/AddUser", {"gid" : group, "uname" : user}, function(data){
			alert(data);
		});
	});	

	$("#fixedButton").click(() => {
		$("input#Title").val("");
		$("input#Content").val("");
		$("input#Status").val("");
		$("input#Assignment").val("");
		$("#newCardModalConfirm").html("Update");
	});
	
	$("#newCardModalConfirm").click(() => {
		var title = $("#title").val();
		var content = $("#content").val();
		var status = $("#status").val();
		var group = sessionStorage.getItem("activeGroup");
		// TODO Get GroupID from GroupName
		var assignment = $("#assignment").val();
		$.post("rest/task/" + title + "/" + content + "/" + status + "/" + groupId + "/" + assignment, function(data){
			alert(data);
		});
	});	
});