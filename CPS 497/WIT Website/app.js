const express = require('express');
const nodemailer = require('nodemailer');
const { google } = require('googleapis');
require('dotenv').config();

console.log(process.env);

const OAuth2 = google.auth.OAuth2;
const app = express();
const port = process.env.PORT || 3000;

const clientId = process.env.CLIENT_ID;
const clientSecret = process.env.CLIENT_SECRET;
const refreshToken = process.env.REFRESH_TOKEN;

const oauth2Client = new OAuth2(
    clientId,
    clientSecret,
    "https://developers.google.com/oauthplayground"
);

oauth2Client.setCredentials({
    refresh_token: refreshToken
});

app.listen(port, () => console.log(`Starting server at ${port}`));
app.use(express.static('public'));
app.use(express.json({limit: '1mb' }))

app.post('/mailing-list', (request, response) => {
    const email = request.body.email;
    sendMailingListMail(email);

    response.json({
        status: "success",
        email: email
    })
});

app.post('/speaker-interest', (request, response) => {
    const name = request.body.name;
    const email = request.body.email;
    const date = request.body.date;

    sendSpeakerMail(name, email, date);

    response.json({
        status: "success",
        name: name,
        email: email,
        date: date
    })
});

function sendMailingListMail(email) {
    const output = `
    <p>Please add the following email address to our mailing list: ${email}</p>`

    const accessToken = oauth2Client.getAccessToken();

    const smtpTransport = nodemailer.createTransport({
        service: "gmail",
        auth: {
            type: "OAuth2",
            user: "cmich.women.in.technology@gmail.com",
            clientId: clientId,
            clientSecret: clientSecret,
            refreshToken: refreshToken,
            accessToken: accessToken
        }
    });

    const mailOptions = {
        from: '"Nodemailer" <cmich.women.in.technology@gmail.com>',
        to: 'cmich.women.in.technology@gmail.com',
        subject: 'New Email Subscription Request',
        text: '',
        html: output
    };

    smtpTransport.sendMail(mailOptions, (error, response) => {
        error ? console.log(error) : console.log(response);
        smtpTransport.close();
    });
}

function sendSpeakerMail(name, email, date) {
    const output = `
    <p>The following speaker request has been submitted:</p>
    <p>Name: ${name}</p>
    <p>Email: ${email}</p>
    <p>Date: ${date}</p>`

    const accessToken = oauth2Client.getAccessToken();

    const smtpTransport = nodemailer.createTransport({
        service: "gmail",
        auth: {
            type: "OAuth2",
            user: "cmich.women.in.technology@gmail.com",
            clientId: clientId,
            clientSecret: clientSecret,
            refreshToken: refreshToken,
            accessToken: accessToken
        }
    });

    const mailOptions = {
        from: '"Nodemailer" <cmich.women.in.technology@gmail.com>',
        to: 'cmich.women.in.technology@gmail.com',
        subject: 'New Speaker Interest Submission',
        text: '',
        html: output
    };

    smtpTransport.sendMail(mailOptions, (error, response) => {
        error ? console.log(error) : console.log(response);
        smtpTransport.close();
    });
}
