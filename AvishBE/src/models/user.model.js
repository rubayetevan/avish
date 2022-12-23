const mongoose = require('mongoose');


const userSchema = new mongoose.Schema({
    userName: {
        required: true,
        type: String,
        unique:true
    },
    email: {
        required: true,
        type: String,
        unique:true
    },
    password: {
        required: true,
        type: String
    },
    isActive:{
        required:false,
        type:Boolean,
        default:true
    },
    isAdmin:{
        required:false,
        type:Boolean,
        default:false
    }
},{timestamps:true})

module.exports = mongoose.model('User', userSchema)