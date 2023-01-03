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
        default: 0
    },
    priceInformation: {
        discountedPrice: {
            required: false,
            type: Number,
            default: 0
        },
        price: {
            required: false,
            type: Number,
            default: 0
        }
    },
    descriptions: {
        description: {
            required: false,
            type: String,
            default: ""
        },
        shortDescription: {
            required: false,
            type: String,
            default: ""
        }
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
        required: false,
        type: String,
        default:""
    },
    categories: { type: Array },
    size: { type: String },
    color: { type: String },
}, { timestamps: true })

module.exports = mongoose.model('Product', productSchema)