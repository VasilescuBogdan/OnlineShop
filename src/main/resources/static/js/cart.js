function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="submit" class="btn btn-primary btn-big" onClick="deleteCartItem()">Delete</button>`;
    parent.appendChild(buttonsTd);
}

const baseURL = 'http://localhost:8090';
$(document).ready(async function() {
    const responseJson = await fetch(
        baseURL + `/api/cart`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = await responseJson.json();
    if (responseJson.ok) {
        console.log(response);
        const table = $("#cart-table tbody");
        for (const item of response) {
            const newProductTr = document.createElement("tr");
            createElementFromAttribute(item.product.name, newProductTr);
            createElementFromAttribute(item.quantity, newProductTr);
            createElementFromAttribute(item.quantity * item.product.price, newProductTr);
            createButtons(newProductTr, product);
            table.append(newProductTr);
        }
    } else {
        console.log("Errror ");
    }
})

