const Product = require('../models/product.model');

async function insertProductToDb(data) {

    const dataToSave = new Product( {
        name: data.name,
        price: data.price,
        description: data.description,
        images:data.images,
        categories:data.categories,
        size:data.size,
        color:data.color
    })

    try {
        const data = await dataToSave.save();
        return data;
    }
    catch (error) {
        return { message: error.message };
    }

}

async function getAllProductFromDb() {
    try {
        const data = await Product.find();
        return data;
    }
    catch (error) {
        return { message: error.message };
    }
}

module.exports ={
    insertProductToDb,
    getAllProductFromDb
}