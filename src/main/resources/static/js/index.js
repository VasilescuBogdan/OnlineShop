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
        const cards = $("#product-cards");
        for (const product of response) {
            const newProductCard = document.createElement("div");
            newProductCard.classList.add("card", "mt-3", "col-md-4", "col-lg-3", "col-xl-3", "ms-5", "text-bg-dark");
            newProductCard.innerHTML = `<img src="..." class="card-img-top" alt="${product.category}">
                                        <h3 class="card-header"> ${product.name} </h3>
                                        <h2 class="card-text text-success ms-3"> ${product.price} $</h2>
                                        <div class="container text-center mb-2">
                                            <div class="row">
                                                <div class="col">
                                                    <a class="btn btn-primary" onclick="productDetails(${product.id})">Details</a>
                                                </div>
                                                <div class="col">
                                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" onclick="showItemDialog(${product.id})">Add to cart</button>
                                                </div>
                                            </div>
                                        </div>`;


            cards.append(newProductCard);
        }
    } else {
        console.log("Errror ");
    }
})

function showItemDialog(id){
    console.log(id);
    $("#input-id").val(id);
    var myModalEl = document.getElementById('item-modal');
    var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function productDetails(id){
    const responseJson = await fetch(
        baseURL + `/api/products/` + id,
        {
            method: 'GET',
            headers:{
                'Content-Type':'application/json'
            },
        });

    const response = await responseJson.json();

    console.log(response);
    $('#name-product').text(response.name);
    $('#provider-product').text(response.provider);
    $('#specifications-product').text(response.specifications);
    $('#category-product').text(response.category);
    $('#price-product').text(response.price + " $");

    const myModalEl = document.getElementById('product-modal');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function addItem(){
    const data = {
        quantity: $('#input-quantity'),
        productId: $('#input-id')
    };

    const responseJson = await fetch(
        baseURL + `/api/cart/`,
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