// ass. Array für alle SVGs (Key: Id, Value: SVG-Content-Objekt)
var allSVG = [];

var cards = [
	new Card('1', 'stuff', 'shit', 'done', 'cool', 'now'),
	new Card('2', 'lul', 'kek', 'work', 'cool', 'now'),
	new Card('3', 'fancy', 'loool', 'work', 'cool', 'now'),
	new Card('4', 'shit', 'blap', 'open', 'cool', 'now')
];

var statColor = {
	"open":"magenta",
	"work":"yellow",
	"done":"lime"
};

function Card(id, title, content, stat, assigned, time) {
	this.id = id;
    this.title = title;
    this.content = content;
    this.stat = stat;
    this.assigned = assigned;
	this.time = time;
}

function getSVG(card) {
	let id = "#" + card.id;
	var doc = $(id)[0].contentDocument;
	console.log(doc);
	return doc;
}

/*function createSVG(card) {
	let id = card.id;
	let object = $("<object></object>")
		.attr("id", card.id)
		.attr("class", card.stat)
		.attr("data", "./task.svg")
		.attr("type", "image/svg+xml")
		.load();
	return object;
}

function appendSVG(card) {
	let id = card.id;
	var object = createSVG(card);
	//console.log(id, object);
	//$("#SVGSPACE").append(object);
}

function changeContent(card) {
	var svg = getSVG(card);
	console.log("Change Content aufgerufen!");
	$("#canvas_background", svg).css("fill", statColor[card.stat]);
	$("#titel", svg).text = card.title;
	$("#beschreibung", svg).text = card.content;
	$("#bearbeiter", svg).text = card.assigned;
	$("#erstellzeit", svg).text = card.time;
	console.log("Change Content durchlaufen!");
}*/

function appendSVG(card) {
	$.get("task.svg", function(data) {
		let cardId = "#" + card.id;
		let wrapperId = "#" + card.stat;
		$(wrapperId).append(data);		
		$(wrapperId + " > #newCard").attr("id",card.id);
		$(cardId).attr("class", card.stat);
		$(cardId + " > #canvas_background").attr("fill", statColor[card.stat]);
		$(cardId + " > #titel").text(card.title);
		$(cardId + " > #beschreibung").text(card.content);
		$(cardId + " > #bearbeiter").text(card.assigned);
		$(cardId + " > #erstellzeit").text(card.time);
	});
}

function appendMultiple(cards) {
	for (var i = 0; i < cards.length; i++)
		appendSVG(cards[i]);
}

function addModals() {
	for (var i = 0; i < allSVG.length; i++) {
		let id = allSVG[i];
		let svg = $("#" + id)[0].contentDocument;
		$("#canvas_background", svg).click( () => {
			$('#modalEdit').modal();
		});
		$("#delete_space", svg).click( () => {
			$('#modalDelete').modal();
		});	
	}
}

function deleteCard(id) {
	$( "#" + id ).remove();
}