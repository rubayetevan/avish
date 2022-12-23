require('dotenv').config();
const User = require('../models/user.model');
const CryptoJS = require('crypto-js');
const jwt = require('jsonwebtoken')

async function login(data) {
    try {
        const user = await User.findOne({ userName: data.userName })
        if (!user) {
            return { errorMessage: "user not found" }
        }
        const hashedPassword = CryptoJS.AES.decrypt(user.password, process.env.APP_SECRET)
        const originalPassword = hashedPassword.toString(CryptoJS.enc.Utf8)
        if (originalPassword !== data.password) {
            return { errorMessage: "Wrong credentials" }
        }
        const { password, ...others } = user._doc;

        const accessToken = jwt.sign({
            id: user._id,
            isAdmin: user.isAdmin,
            isActive: user.isActive
        }, process.env.APP_SECRET, { expiresIn: "3d" })

        return {...others,accessToken};
    }
    catch (error) {
        return { errorMessage: error.message };
    }
}

module.exports = {
    login
}