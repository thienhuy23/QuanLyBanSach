const user_id = $('input[name=user_id]').val();

let repo = JSON.parse(localStorage.getItem(`cart_${user_id}`));
const data = JSON.stringify(repo.map(s => parseInt(s.id))).replace("[", "").replace("]", "");
let result = [];
axios.get("/cart/get?arr=" + data).then((result) => {
	fillData(result.data);
    $('#frmTT').submit(function (e) { 
        $("input[type=checkbox]:checked").each(function () {
            let id = $(this).attr('id').split("-")[1];
            let quantity = $(this).attr('id').split("-")[2];
            $('#frmTT').prepend(`<input type="hidden" name="cart" value="${id}_${quantity}"/>`)
        });
        // e.preventDefault();
    });
}).catch((err) => {
	console.log(err);
});;

let tt = 0;
const Delete = (id) => {
	let arr = repo.filter(s => s.id != id);
	localStorage.setItem(`cart_${user_id}`, JSON.stringify(arr));
	window.location.reload();
}


const fillData = (data) => {
	data.forEach((s, i) => {
		$("#body").append(`
        <tr>
        <td><input type="checkbox" name="state" id="chk-${s.id}-${repo[i].quantity}"/></td>
        <td>${s.id}</td>
        <td style="width:200px">
            <img class="w-50 d-block mx-auto" src="${s.images[0].url}">
        </td>
        <td>${s.name}</td>
        <td>${repo[i].quantity}</td>
        <td>${s.price.toLocaleString('it-IT', { style: 'currency', currency: 'VND' })}</td>
        <td>${(parseInt(repo[i].quantity) * parseInt(s.price)).toLocaleString('it-IT', { style: 'currency', currency: 'VND' })}</td>
        <td>
            <button onclick="Delete(${s.id})" class="btn is-outline-pink text-white"> Xóa</button>
        </td>
    </tr>
        `);
        tt +=  parseInt(s.price) * parseInt(repo[i].quantity);
	});
	$("#tt").text(tt.toLocaleString('it-IT', { style: 'currency', currency: 'VND' }));
}
/* <td>${parseInt(repo[i].quantity)*parseInt(s.price)}</td>*/

