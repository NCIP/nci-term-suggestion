
function displayVocabLinkInNewWindow(id) {
	var element = document.getElementById(id);
	var url = element.value;
	if (url != "")
	element.onclick=window.open(url);
	else
	alert("This vocabulary does not have\nan associated home page.");
}

function go(loc) {
	window.location.href = loc;
}


function closeWindow() {
	window.open('','_parent','');
	window.close();
}


function clear_form()
	{
	if (confirm("Are you sure you want to clear form?")) {
	    document.suggestion.reset();
	}
	return false;
}

function clear_cdisc_form()
	{
	if (confirm("Are you sure you want to clear form?")) {
	    document.suggestion.reset();
	}
	return false;
}

