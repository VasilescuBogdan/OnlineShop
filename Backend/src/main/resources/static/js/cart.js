function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="submit" class="btn btn-primary btn-big" onClick="deleteCartItem(${data.id})">Delete</button>`;
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
        var total = 0;
        for (const item of response) {
            const newItemTr = document.createElement("tr");
            const subtotal = item.quantity * item.product.price
            createElementFromAttribute(item.product.name, newItemTr);
            createElementFromAttribute(item.quantity, newItemTr);
            createElementFromAttribute(subtotal + " $", newItemTr);
            total+=subtotal;
            createButtons(newItemTr, item);
            table.append(newItemTr);
        }
        const footer = document.createElement(("tr"));
        var cell = document.createElement(("td"));
        cell.innerHTML = `<h4> Total </h4>`;
        footer.appendChild(cell);
        cell = document.createElement("td");
        cell.innerHTML = `<h5> ${total} $</h5>`;
        footer.appendChild(cell);
        table.append(footer);
    } else {
        console.log("Errror ");
    }
})

async function deleteCartItem(id){

    const responseJson = await fetch(
        baseURL + `/api/cart/` + id,
        {
            method: 'DELETE',
            headers: {
                'Content-Type' : 'application/json'
            },
        });
    console.log(responseJson);

    window.location.reload();
}

async function deleteAll(){

    const responseJson = await fetch(
        baseURL + `/api/cart`,
        {
            method: 'DELETE',
            headers: {
                'Content-Type' : 'application/json'
            },
        });
    console.log(responseJson);

    window.location.reload();
}

