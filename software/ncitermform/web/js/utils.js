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
  if (url != "")
    element.onclick=window.open(url);
  else
	alert("URL not set.");
}

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


//----------------------------------------------------------------------------//
function closeWindow() {
  window.open('','_parent','');
  window.close();
}


   function check_blank()
   {
      var email=document.getElementById("email").value;  
      if (email == "")
      {
          alert("Please provide an email address.");
          //if(navigator.appName == "Microsoft Internet Explorer")
	  {
              document.getElementById("email").focus();
          } 
          return false;
      }   
   
      var term=document.getElementById("term").value;  
      if (term == "")
      {
          alert("Please enter a term.");
          //if(navigator.appName == "Microsoft Internet Explorer")
	  {
              document.getElementById("term").focus();
          }        
          return false;
      } 
      
      var answer=document.getElementById("answer").value;  
      if (answer == "")
      {
          alert("Please enter the characters appearing in the image.");
          //if(navigator.appName == "Microsoft Internet Explorer")
	  {
              document.getElementById("answer").focus();
          } 
          return false;
      }
      return true;
   }
   

   function clear_form()
   {
      if (confirm("Are you sure you want to clear form?")) {
	    document.suggestion.reset();
      }
      return false;
   }


   function try_again()
   {
       if(navigator.appName == "Microsoft Internet Explorer")
       {
      	    parent.history.back(); 
       }    
       return false;
   }
   
   function reloadPage()
   {
       window.location.reload();
   }
   