require('dotenv').config();
const User = require('../models/user.model');
const CryptoJS = require('crypto-js');

async function registerUser(data) {

    const dataToSave = new User({
        userName: data.userName,
        email: data.email,
        password: CryptoJS.AES.encrypt(data.password, process.env.APP_SECRET).toString(),
        isAdmin: data.isAdmin
    })

    try {
        const data = await dataToSave.save();
        return data;
    }
    catch (error) {
        return { errors: [error] };
    }
}



module.exports = {
    registerUser
}