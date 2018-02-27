if ('WebSocket' in window) {
	websocket = new WebSocket("ws://localhost:8080/WebSockets/echo/myChat");
    //alert('WebSocket');
} else if ('MozWebSocket' in window) {
	websocket = new MozWebSocket("ws://localhost:8080/WebSockets/echo/myChat");
    //alert('MozWebSocket');
} else {
     alert('WebSocket is not supported by this browser.');
}

websocket.onopen = function onOpen() {
	if (websocket != null) {			
            var p = document.createElement("p");
		    p.innerHTML = "Verbunden!";
		    document.getElementById("outputtext").appendChild(p);
	}else {alert("WebSocket does not work!")} };
	
websocket.onmessage = function onMessage(evt) {	        
			var p = document.createElement("p");
			p.innerHTML = evt.data;
			document.getElementById("outputtext").appendChild(p);
			};
			
function send() {
			var name = document.getElementById("Username");
			var inputtext = document.getElementById("msg");
			websocket.send(name.value + ": " + inputtext.value);
			};