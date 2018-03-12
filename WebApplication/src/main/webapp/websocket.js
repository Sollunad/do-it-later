var websocket;
var name;
var group;
var timerID = 0;


$(function(){
	
	name = sessionStorage.getItem("userName");
	group = sessionStorage.getItem("activeGroup");
	$("#username").text(name);
	$("#act_group").text(group);
	
	if ("WebSocket" in window){
		websocket = new WebSocket("ws://localhost:8080/WebSockets/echo/" + group);
	}else{
		console.log("Browser doesn't support WebSockets.");
	}

	websocket.onopen = function(){
		websocket.send(name + " joined the Room.");
		keepAlive();
	};

	websocket.onerror = function(error){
		console.log("An error uccorred: " + error);
	};

	websocket.onmessage = function(event){
		if(event.data != ""){
			$("#outputtext").append("<div class='row message-bubble col-lg-12'>\n<p>"+ event.data +"</p>\n</div>");
		}
	};
	
	websocket.onclose = function(){
		cancelAlive();
	};
	
	$("#msg").keypress(function (e) {
		  if (e.which == 13) {
		    send();
		  }
		});
});

function send(){
	var message = $("#msg").val();
	websocket.send(name + ": " + message);
	$("#msg").val("");
};

function keepAlive() { 
    var timeout = 1000;  
    if (websocket.readyState == websocket.OPEN) {  
    	websocket.send("");  
    }  
    timerID = setTimeout(keepAlive, timeout);  
};

function cancelAlive() {  
    if (timerID) {  
        clearTimeout(timerID);  
    }  
};