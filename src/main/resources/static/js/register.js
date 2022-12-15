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

    const response = responseJson.JSON;
    console.log(responseJson);

    window.location.replace(baseURL+"/index.html");
}