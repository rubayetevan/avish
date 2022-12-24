const mongoose = require('mongoose');


const productSchema = new mongoose.Schema({
    name: {
        required: true,
        type: String,
        unique: true
    },
    quantity: {
        required: false,
        type: Number,
        default:0
    },
    price: {
        required: true,
        type: Number
    },
    desc: {
        required: false,
        type: String
    },
    isActive: {
        required: false,
        type: Boolean,
        default: true
    },
    images: {
        thumb: String,
        pics: { type: Array },
    },
    brand: {
        required: true,
        type: String
    },
    categories: { type: Array },
    size: { type: String },
    color: { type: String },
}, { timestamps: true })

module.exports = mongoose.model('Product', productSchema)