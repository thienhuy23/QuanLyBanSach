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

function add(user_id) {
	if(user_id ==null){
		location.href="/login";
	}
	let arr = new Array();
	var book1 = $('input[name=book]').val();
	var book2 = window.location.search.split("=")[1];
	var mydata = {
		id: book2,
		quantity: book1
	}
	let check = 0;
	let local = localStorage.getItem(`cart_${user_id}`);
	if (local != null) {
		arr = JSON.parse(local);
		arr.forEach(s => {
			if (parseInt(s.id) === parseInt(mydata.id)) {
				s.quantity = parseInt(s.quantity) + parseInt(mydata.quantity);
				check++;
			}
		});
	}
	if (check > 0) {
		localStorage.setItem(`cart_${user_id}`, JSON.stringify(arr));
	} else {
		localStorage.setItem(`cart_${user_id}`, JSON.stringify([...arr, mydata]));
	}


}

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

