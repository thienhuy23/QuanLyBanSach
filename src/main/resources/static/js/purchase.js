const user_id = $('input[name=user_id]').val();

let repo = JSON.parse(localStorage.getItem(`cart_${user_id}`)==null?[]:localStorage.getItem(`cart_${user_id}`));
const data = JSON.stringify(repo.map(s=>parseInt(s.id))).replace("[","").replace("]","");

let result = [];


// $(document).ready(function () {
//     let tt = 0;
//     $('span.quantity').each(function(){
//         let id = $(this).attr("id").split('_')[1];
//         let price= $(this).attr("id").split('_')[2];
//         let repo = JSON.parse(localStorage.getItem(`cart_${user_id}`)==null?[]:localStorage.getItem(`cart_${user_id}`));

//         repo.filter(s=>s.id==id).forEach(s=>{
//             $(this).text(s.quantity);
//             tt+=parseInt(s.quantity) * parseInt(price);
//         })
//     });
//     $("#mt").text(tt);
//     $("#cost").text(tt+35000);
// });

let mt = 0;


$("#frmBill").submit(function (e) {
    localStorage.removeItem(`cart_${user_id}`);
}); 