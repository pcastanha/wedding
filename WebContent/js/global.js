/*

November Template

http://www.templatemo.com/tm-473-november

*/

/* Google Maps
------------------------------------------------*/
var map = '';
var center;

function initialize() {
  var mapOptions = {
    zoom: 15,
    center: new google.maps.LatLng(-21.1133077,-44.1843081),
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
    position: new google.maps.LatLng(-21.1133077,-44.1843081),
    title: "Casamento Mariana & Pedro <3",
    map: map,
    icon: 'img/bike-zone-signal.png'
	});
	
	var boxText = document.createElement("div");
		boxText.style.cssText = "border: 1px solid black; margin-top: 8px; background: #305174; padding: 5px; color: white;"; 
		boxText.innerHTML = "<div style=\"text-align: center;\">Local do Evento, Tiradentes</div><br><img border=\"0\" src=\"img/festa-tiradentes-sm.jpg\" alt=\"BAR\" width=\"304\" height=\"228\" style=\"margin-left: 11px;\"><br>Pousada onde será realizado o casamento<br>Rua Santíssima Trindade, 1000 - Tiradentes, Minas Gerais";

	var myOptions = {
			content: boxText,
			disableAutoPan: false,
			maxWidth: 360,
			pixelOffset: new google.maps.Size(0, 0),//X,Y axis
			zIndex: null,
			boxStyle: { 
			  background: "url('tipbox.gif') no-repeat",
			  opacity: 0.75,
			  width: "180px"
			 },
			closeBoxMargin: "10px 2px 2px 2px",
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

/* onScroll function
----------------------------------------*/
function onScroll(event){
  var scrollPosition = $(document).scrollTop();
  $('nav li a').each(function () {
    var currentLink = $(this);
    var refElement = $(currentLink.attr("href"));
    if (refElement.position().top <= scrollPosition && refElement.position().top + refElement.height() > scrollPosition) {
      $('nav ul li').removeClass("active");
      currentLink.parent().addClass("active");
    }
    else{
      currentLink.parent().removeClass("active");
    }
  });
}

/* HTML Document is loaded and DOM is ready.
--------------------------------------------*/
$(document).ready(function(){
  //slider
  var sudoSlider = $("#slider").sudoSlider({
   effect: "fade",
   pause: 3000,
   auto:true,
   continuous:true
 });

  //mobilemenu
  $('.mobile').click(function(){
    var $self = $(this);
    $('.menumobile').slideToggle( function(){
      $self.toggleClass('closed');
    });
  });

  //navigation script
  $('.Navigation ul li a').click(function(){
    $('.menumobile').removeAttr("style");
    $('#mobile_sec .mobile').removeClass("closed");
  });

  $('a.slicknav_btn').click(function(){
    $(".mobilemenu ul").css({"display":"block"});
  });

  //tab
  $(".tabLink").each(function(){
    $(this).click(function(){
      tabeId = $(this).attr('id');
      $(".tabLink").removeClass("activeLink");
      $(this).addClass("activeLink");
      $(".tabcontent").addClass("hide");
      $("#"+tabeId+"-1").removeClass("hide");
      return false;
    });
  });
  $('a[href*=#]:not([href=#])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'')
      || location.hostname == this.hostname)
    {
      var target = $(this.hash),
      headerHeight = $(".primary-header").height() + 5; // Get fixed header height
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length)
      {
        $('html,body').animate({
          scrollTop: target.offset().top + 2
        }, 600);
        return false;
      }
    }
  });	

  //Header Small
  window.addEventListener('scroll', function(e){
    var distanceY = window.pageYOffset || document.documentElement.scrollTop,
    shrinkOn = 50;
    
    if (distanceY > shrinkOn) {
      $('header').addClass("smaller");
    } else {
      $('header').toggleClass("smaller");
    }
  });
  
  loadGoogleMap();

}); 

$(document).on("scroll", onScroll);

// Complete page is fully loaded, including all frames, objects and images
$(window).load(function() {
  // Remove preloader
  // https://ihatetomatoes.net/create-custom-preloading-screen/
  $('body').addClass('loaded');
});

