document.getElementById('speakerInterestForm').addEventListener('submit', submitForm);


// Submit form
function submitForm(e) {
    e.preventDefault();

    var name = document.getElementById('speakerName').value;
    var email = document.getElementById('speakerEmail').value;
    var date = document.getElementById('speakerDate').value;

    console.log("Name: " + name);
    console.log("Email: " + email);
    console.log("Date: " + date);

    sendEmail(name, email, date);

    document.getElementById('speakerInterestForm').reset();
}

async function sendEmail(name, email, date) {
    const data = { name, email, date };
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    const response = await fetch('/speaker-interest', options);
    const json = await response.json();
    console.log(json);
}