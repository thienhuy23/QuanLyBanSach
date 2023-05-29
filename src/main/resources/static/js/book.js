let items = document.querySelectorAll('.carousel .carousel-item')

items.forEach((el) => {
	const minPerSlide = 4
	let next = el.nextElementSibling
	for (var i = 1; i < minPerSlide; i++) {
		if (!next) {
			// wrap carousel by using first child
			next = items[0]
		}
		let cloneChild = next.cloneNode(true)
		el.appendChild(cloneChild.children[0])
		next = next.nextElementSibling
	}
});


const clickPhoto = (url) => {
	console.log(url);
	$("#photo").attr("src", url);
}

var addBook = $('button[name=add]');
addBook.on('click', function() {
	var book1 = $('input[name=book]').val();
	var book2 = window.location.search.split("=")[1];
	var mydata = {
		id: book1,
		quantity: book2
	}
	localStorage.setItem("mydata", JSON.stringify(mydata));

	const response = axios.post('/cart', JSON.parse(localStorage.getItem("mydata")));
	console.log(response)
});

function openCity(evt, cityName) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the link that opened the tab
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}

