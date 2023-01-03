const Product = require('../models/product.model');

async function insertProductToDb(data) {

    const dataToSave = new Product( {
        name: data.name,
        priceInformation: data.priceInformation,
        descriptions: data.descriptions,
        images:data.images,
        categories:data.categories,
        size:data.size,
        color:data.color,
        brand:data.brand,
    })

    try {
        const data = await dataToSave.save();
        return data;
    }
    catch (error) {
        return { message: error.message };
    }

}

async function getAllProductFromDb(data) {

    const pageNum = data.page || 1;
    const itemsPerPage = 10;
    
    try {
        const data = await Product.find().limit(itemsPerPage).skip(itemsPerPage * (pageNum-1));
        return data;
    }
    catch (error) {
        return { errors: [error] };
    }
}

module.exports ={
    insertProductToDb,
    getAllProductFromDb
}