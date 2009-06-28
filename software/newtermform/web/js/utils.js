//----------------------------------------------------------------------------//
// Note: The Firefox browser is initial configure to display a URL
//   within a separate tab when calling the window.open method.
//   To configure Firefox display within a separate window:
//     * Start up Firefox,
//     * Select Menubar --> Tools --> Options,
//     * Select "Tabs" tab,
//     * Select "a new window" radio button.
//----------------------------------------------------------------------------//
function displayLinkInNewWindow(id) {
  var element = document.getElementById(id);
  var url = element.value;
  element.onclick=window.open(url + "_blank");
}

function go(loc) {
  window.location.href = loc;
}