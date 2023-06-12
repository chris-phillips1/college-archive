document.getElementById('emailForm').addEventListener('submit', submitForm);


// Submit form
function submitForm(e) {
    e.preventDefault();

    var email = document.getElementById('formEmail').value;
    console.log("Client: " + email);

    sendEmail(email);

    document.getElementById('emailForm').reset();
}

async function sendEmail(email) {
    const data = { email };
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    const response = await fetch('/mailing-list', options);
    const json = await response.json();
    console.log(json);
}