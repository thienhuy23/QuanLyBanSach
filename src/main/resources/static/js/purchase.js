let repo = JSON.parse(localStorage.getItem("mydata")==null?[]:ocalStorage.getItem("mydata"));
const data = JSON.stringify(repo.map(s=>parseInt(s.id))).replace("[","").replace("]","");
let result = [];
const fillData = (data) =>{
    data.forEach((s,i) => {
        $("#body").append(`
        <tr>
        <td>
            <div class="icon">
                <div href="#" class="position-relative pt-1">
                    <span class="fs-4 text-dark ii"><img
                        src="${s.images[0].url}"
                        style="width: 100px" class="rounded float-start" alt="..."></span>
                    <span class="position-absolute top-5 start-200 translate-middle badge rounded-pill bg-secondary">
                        ${repo[i].quantity} </span>
                </div>
            </div>
        </td>
        <td style="padding-left: -10px;" class="">${s.name}</td>
        <td class="ms-4" style="padding-left: 8em;">${s.price.toLocaleString('it-IT', { style: 'currency', currency: 'VND' })}</td>
    </tr>
        `);
		mt += parseInt(s.price) * parseInt(repo[i].quantity);
	});
	$("#mt").text(mt.toLocaleString('it-IT', { style: 'currency', currency: 'VND' }));
	$("#cost").text((mt + 35000).toLocaleString('it-IT', { style: 'currency', currency: 'VND' }));
}
const init =async()=> {   
    result =await axios.get("/cart/get?arr="+data);
    console.log(result.data);
    result = result.data;
    fillData(result);

};
init();
// $(document).ready(async function () {


let mt = 0;


const pay = async (username) => {

    let bdt1 = [];


    result.forEach((s,i)=>{
        bdt1.push({
            book_id:s.id,
            quantity:repo[i].quantity
        });
    });

    const data={
        receive_place:$("input[name='receive_place']").val(),
        // user_id:username,
        sum:mt+35000,
        bdt:bdt1
    };
    console.log(data);
    await axios.post("/purchase?user_id="+username,data);
    localStorage.removeItem("myData");
    // location.href = "/ORDER_USER";
}