const data = JSON.stringify([1,2]);

let result = [];
axios.get("/cart/get?arr="+data.replace("[","").replace("]","")).then((result) => {
    fillData(result.data);
    console.log(result.data);
}).catch((err) => {
    console.log(err);
});;


const fillData = (data) =>{
    data.forEach(s => {
        $("#body").append(`
        <tr>
        <td>${s.id}</td>
        <td style="width:200px">
            <img class="w-50 d-block mx-auto" src="${s.images[0].url}">
        </td>
        <td>${s.name}</td>
        <td>2</td>
        <td>${s.price}</td>
        <td>10000</td>
        <td>
            <a class="btn is-outline-pink text-white"> Xóa</a>
        </td>
    </tr>
        `);
    });
}

