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
            newProductCard.classList.add("card", "mt-3", "col-md-4", "col-ms-6");
            newProductCard.innerHTML = `<img src="..." class="card-img-top" alt="...">
                                        <h5 class="card-title"> ${product.name}</h5>
                                        <p class="card-text"> ${product.price} $</p>
                                        <a href="#" class="btn btn-primary" onclick="productDetails(${product.name})">Details</a>
                                        <a href="#" class="btn btn-primary">Buy</a>`;
            cards.append(newProductCard);
        }
    } else {
        console.log("Errror ");
    }
})

async function productDetails(name){
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
    $('#name-product').val(response.name);
    $('#provider-product').val(response.provider);
    $('#specifications-product').val(response.specifications);
    $('#category-product').val(response.category);
    $('#price-product').val(response.price);

    const myModalEl = document.getElementById('product-modal');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}