$(function(){
	
	multipleCards(cards);
	multipleGroups(groups);	
	var name = sessionStorage.getItem("userName");
	updateUsername(name);
	
	$.post("rest/GetGroups", { "uid" : name },
			function(data) {
				if (data) {
					multipleGroups(data);
				} else {
					alert("Gruppen holen fehlgeschlagen!");
				}
			}
		);
	});
	
	§(".change_group").click(function(){
		var group = $(this).text();
		sessionStorage.setItem("activeGroup", group);
		updateActiveGroup(group);
		//TODO
	});
	
	
	
});