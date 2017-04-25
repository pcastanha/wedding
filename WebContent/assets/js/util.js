/**
 * 
 */

function sendConfirmation(){
	
}

var map = '';
var center;

function initialize() {
  var mapOptions = {
    zoom: 14,
    center: new google.maps.LatLng(-20.0416731,-43.9159294),
    scrollwheel: false,
    draggable:true
  };

  map = new google.maps.Map(document.getElementById('GoogleMap'),  mapOptions);

  google.maps.event.addDomListener(map, 'idle', function() {
    calculateCenter();
  });

  google.maps.event.addDomListener(window, 'resize', function() {
    map.setCenter(center);
  });
  
  var marker = new google.maps.Marker({
    position: new google.maps.LatLng(-20.0416731,-43.9159294),
    title: "Casamento Mariana & Pedro <3",
    map: map,
    icon: 'images/bike-zone-signal.png'
	});
	
	var boxText = document.createElement("div");
		boxText.style.cssText = "border: 1px solid black; margin-top: 8px; background: #305174; padding: 5px; color: white;"; 
		boxText.innerHTML = "<div style=\"text-align: center;\"><b><i>Pousada Vila Solaris</b></i></div><br>R. Francisca Rezadeira, 11 <br>São Sebastiao das Águas Claras <br>Nova Lima - MG";

	var myOptions = {
			content: boxText,
			disableAutoPan: false,
			maxWidth: 260,
			pixelOffset: new google.maps.Size(0, 0),//X,Y axis
			zIndex: null,
			boxStyle: { 
			  background: "url('tipbox.gif') no-repeat",
			  opacity: 0.75,
			  width: "180px"
			 },
			closeBoxMargin: "5px 2px 2px 2px",
			closeBoxURL: "http://www.google.com/intl/en_us/mapfiles/close.gif",
			infoBoxClearance: new google.maps.Size(10, 10),
			isHidden: false,
			pane: "floatPane",
			enableEventPropagation: false
	};

	var iw = new google.maps.InfoWindow(myOptions);
	
	google.maps.event.addListener(marker, 'click', function(){
        iw.open(map, marker);
    });
}

function calculateCenter() {
  center = map.getCenter();
}

function loadGoogleMap(){
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDLboVTfMwyTJaSLJ9vOO_Dhe9Z4CcBIyA&v=3.exp&' + 'callback=initialize';
  document.body.appendChild(script);
}

$(document).ready(function() {
	loadGoogleMap();
});