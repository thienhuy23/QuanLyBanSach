// const countCart = () => { 
$(document).ready(function(){
    const user_id1= $("input[name=user_id]").val();

    let size = 0;
    let cart = localStorage.getItem(`cart_${user_id1}`);
    if (cart != null) {
        console.log(1);
        size = JSON.parse(cart).length;
    }
    console.log(size);
    $("span#cartN").text(size);
});



// }