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

var statDiv = {
	"open":"open_tasks",
	"work":"working_tasks",
	"done":"done_tasks"
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

function appendSVG(card) {
	$.get("task.svg", function(data) {
		let cardId = "#" + card.id;
		let wrapperId = "#" + statDiv[card.stat];
		$(wrapperId).append(data);		
		$(wrapperId + " > #newCard").attr("id",card.id);
		$(cardId).attr("class", card.stat);
		
		// Gestaltung
		$(cardId + " > #canvas_background").attr("fill", statColor[card.stat]);
		$(cardId + " > #titel").text(card.title);
		$(cardId + " > #beschreibung").text(card.content);
		$(cardId + " > #bearbeiter").text(card.assigned);
		$(cardId + " > #erstellzeit").text(card.time);
		
		// Click-Events hinzufügen
		$(cardId + " > #canvas_background").click( () => {
			$('#newCardModal').modal();
		});
		$(cardId + " > #delete_space").click( () => {
			var answer = confirm("Delete Card?");
		});
		
	});
}

function appendMultiple(cards) {
	for (var i = 0; i < cards.length; i++)
		appendSVG(cards[i]);
}

function deleteCard(id) {
	$( "#" + id ).remove();
}