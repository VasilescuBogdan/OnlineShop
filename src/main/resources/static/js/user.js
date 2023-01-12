const baseURL = 'http://localhost:8090';

async function SignUp(){
    const data = {
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        email: $('#inputEmail').val(),
        number: $('#inputNumber').val(),
        password: $('#inputPassword').val(),
    };

    const responseJson = await fetch(
        baseURL + `/api/users`,
        {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
    console.log(responseJson);

    window.location.replace(baseURL);
}

$(document).ready(async function() {
    const responseJson = await fetch(
        baseURL + `/api/users`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = await responseJson.json();
    if (responseJson.ok) {
        console.log(response);
        $('#profileEmail').text('Email: ' + response.email);
        $('#profileFirstName').text('First name: ' + response.firstName);
        $('#profileLastName').text('Last name: ' + response.lastName);
        $('#profileNumber').text('Tel: ' + response.number);
        $('#profileCreationDate').text('Created at: ' + response.creationDate);
        $('#profilePoints').text('Points: ' + response.points);
        }
    else {
        console.log("Errror ");
    }
});