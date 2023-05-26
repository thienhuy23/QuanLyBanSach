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


