function showLarge (id) {

	hideAll();

	document.getElementById(id).style.display = "block";
}

function hideAll(){

	var elements = document.getElementsByClassName("large");

	for (var i = 0; i < elements.length; i++) {
		elements[i].style.display = "none";
	}

}