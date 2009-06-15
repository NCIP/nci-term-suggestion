 function displayLinkInNewWindow(id) {
  var element = document.getElementById(id);
  var url = element.value;
  element.onclick=window.open(url + "_blank");
 }
 