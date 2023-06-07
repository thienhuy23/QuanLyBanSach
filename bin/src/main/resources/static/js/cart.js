let repo = JSON.parse(localStorage.getItem("mydata"));
const data = JSON.stringify(repo.map(s=>parseInt(s.id))).replace("[","").replace("]","");
let result = [];
axios.get("/cart/get?arr="+data).then((result) => {
    fillData(result.data);
    console.log(result.data);
}).catch((err) => {
    console.log(err);
});;

const Delete = (id) =>{
    let arr = repo.filter(s=>s.id!=id);
    localStorage.setItem("mydata",JSON.stringify(arr));
    window.location.reload();
}

const fillData = (data) =>{
    data.forEach((s,i) => {
        $("#body").append(`
        <tr>
        <td>${s.id}</td>
        <td style="width:200px">
            <img class="w-50 d-block mx-auto" src="${s.images[0].url}">
        </td>
        <td>${s.name}</td>
        <td>${repo[i].quantity}</td>
        <td>${s.price}</td>
        <td>${parseInt(repo[i].quantity)*parseInt(s.price)}</td>
        <td>
            <button onclick="Delete(${s.id})" class="btn is-outline-pink text-white"> Xóa</button>
        </td>
    </tr>
        `);
    });
}
