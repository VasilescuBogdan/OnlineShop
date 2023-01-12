function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onClick="updateProductInit(${data.id})">Update</button>
    <button type="submit" class="btn btn-primary btn-big" onClick="deleteProduct(${data.id})">Delete</button>`;
    parent.appendChild(buttonsTd);
}

const baseURL = 'http://localhost:8090';
$(document).ready(async function() {
    const responseJson = await fetch(
        baseURL + `/api/products`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = await responseJson.json();
    if (responseJson.ok) {
        console.log(response);
        const table = $("#products-table tbody");
        for (const product of response) {
            const newProductTr = document.createElement("tr");
            createElementFromAttribute(product.name, newProductTr);
            createElementFromAttribute(product.category, newProductTr);
            createElementFromAttribute(product.provider, newProductTr);
            createElementFromAttribute(product.specifications, newProductTr);
            createElementFromAttribute(product.stock, newProductTr);
            createElementFromAttribute(product.price, newProductTr);
            createButtons(newProductTr, product);
            table.append(newProductTr);
        }
    } else {
        console.log("Errror ");
    }
})

async function addProduct(){
    const data = {
        name: $('#inputName').val(),
        category: $('#inputCategory').val(),
        provider: $('#inputProvider').val(),
        specifications: $('#inputSpecifications').val(),
        stock: $('#inputStock').val(),
        price: $('#inputPrice').val()
    };

    const responseJson = await fetch(
        baseURL + `/api/products`,
        {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
    console.log(responseJson);

    window.location.reload();
}

async function updateProductInit(id){

    console.log(id);
    const data = await fetchData(id);
    $('#inputUpdateId').val(id);
    $("#inputUpdateName").val(data.name);
    $("#inputUpdateCategory").val(data.category);
    $("#inputUpdateProvider").val(data.provider);
    $("#inputUpdateSpecifications").val(data.specifications);
    $("#inputUpdateStock").val(data.stock);
    $("#inputUpdatePrice").val(data.price);

    const myModalEl = document.getElementById('update-modal');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchData(id){
    const responseJson = await fetch(
        baseURL + `/api/products/` + id,
        {
            method: 'GET',
            headers:{
                'Content-Type':'application/json'
            },
        });
    return await responseJson.json();
}

async function updateProduct(){

    const data = {
        id: $('#inputUpdateId').val(),
        name: $('#inputUpdateName').val(),
        category: $('#inputUpdateCategory').val(),
        provider: $('#inputUpdateProvider').val(),
        specifications: $('#inputUpdateSpecifications').val(),
        stock: $('#inputUpdateStock').val(),
        price: $('#inputUpdatePrice').val()
    };

    const responseJson = await fetch(
        baseURL + `/api/products`,
        {
            method: 'PUT',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
    console.log(responseJson);
    window.location.reload();

}

async function deleteProduct(id){

    const responseJson = await fetch(
        baseURL + `/api/products/` + id,
        {
            method: 'DELETE',
            headers: {
                'Content-Type' : 'application/json'
            },
        });
    console.log(responseJson);

    window.location.reload();
}

