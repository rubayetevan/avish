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

        const { password, ...others } = data._doc;
        return {...others};
    }
    catch (error) {
        return { errors: [error] };
    }
}

async function updateUser(data) {
    try {
        const updatedUser = await User.findByIdAndUpdate(data._id,{
            $set:data
        },{new:true});

        const { password, ...others } = updatedUser._doc;
        return {...others};
    }
    catch (error) {
        return { errors: [error] };
    }
}


module.exports = {
    registerUser,updateUser
}