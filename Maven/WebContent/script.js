// ass. Array für alle SVGs (Key: Id, Value: SVG-Content-Objekt)
var allSVG = {};

var cards = [
	new Card('1', 'Datenbank aufsetzen', 'NoSQL = toll', 'done', 'jessejrichter', 'now'),
	new Card('2', 'Design überarbeiten', '...bevors jemand falsch macht.', 'work', 'jonasseng', 'cool', 'now'),
	new Card('3', 'SVGs liebhaben', 'I <3 SVG', 'work', 'danielklaus', 'now'),
	new Card('4', 'Kaffee kochen', 'KAFFEEE!', 'open', 'praktikant', 'now')
];

var groups = [
	new Group('1', 'Webengineering Projekt I', 'stefanreschke'),
	new Group('2', 'Compilerbau Projekt III', 'danielklaus'),
	new Group('3', 'Homepages für die Arbeit', 'jessejrichter')
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

function Group(id, name, admin) {
	this.id = id;
	this.name = name;
	this.admin = admin;	
};

function appendSVG(card) {
	allSVG[card.id] = card;
	$.get("task.svg", (data) => {
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
		$(cardId + " > #canvas_background").click(() => {
			$("input#title").val(card.title);
			$("input#content").val(card.content);
			$("input#status").val(card.stat);
			$("input#assignment").val(card.assigned);
			$("#newCardModalConfirm").html("Update");
			$("#newCardModalConfirm").unbind("click").click(() => { updateSVG(cardId, card); });
			$("#newCardModal").modal();
		});
		$(cardId + " > #delete_space").click( () => {
			if (confirm("Delete Card?")) {
				$(cardId).remove();
			}
		});		
	});
}

function updateSVG(cardId, card) {
	$(cardId).remove();
	var newCard = new Card(
		card.id,
		$("input#title").val(),
		$("input#content").val(),
		$("input#status").val(),
		$("input#assignment").val(),
		card.time
	);
	appendSVG(newCard);
}

function appendGroup(group) {
	$("#Group_List").append(
		$("<li></li>").html(
			$("<a></a>")
				.attr("href", "")
				.html(group.name)
		)
	);
}

function multipleCards(cards) {
	for (var i = 0; i < cards.length; i++)
		appendSVG(cards[i]);
}

function multipleGroups(groups) {
	for (var i = 0; i < groups.length; i++)
		appendGroup(groups[i]);	
}