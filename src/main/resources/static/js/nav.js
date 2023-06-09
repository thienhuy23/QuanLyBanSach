// const countCart = () => { 
$(document).ready(function(){
    let size = 0;
    let cart = localStorage.getItem("mydata");
    if (cart != null) {
        size = JSON.parse(cart).length;
    }
    console.log(size);
    $("span#cartN").text(size);
});



// }