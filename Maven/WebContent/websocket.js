var websocket;
var name;
var group;
var timerID = 0; 

$(function(){
	
	name = $("#username").text();
	group = $("#act_group").text();
	
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
		$("#outputtext").append("<div class='row message-bubble col-lg-12'>\n<p>"+ event.data +"</p>\n</div>");
	};
	
	websocket.onclose = function(){
		cancelAlive();
	};
});

function send(){
	var message = $("#msg").val();
	websocket.send(name + ": " + message);
};

function keepAlive() { 
    var timeout = 1000;  
    if (websocket.readyState == websocket.OPEN) {  
    	websocket.send("PING");  
    }  
    timerID = setTimeout(keepAlive, timeout);  
};

function cancelAlive() {  
    if (timerID) {  
        clearTimeout(timerID);  
    }  
};