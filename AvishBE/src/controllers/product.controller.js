const productService = require('../services/product.service')


async function getAllProducts(req, res, next) {
  try {
    res.json(await productService.getAllProductFromDb(req.params));
  } catch (err) {
    console.error(`Error while getting programming languages`, err.message);
    next(err);
  }
}

async function getProduct(req, res, next) {
  try {
    res.json({method:"getProduct",id:req.params.id});
  } catch (err) {
    console.error(`Error while getting programming languages`, err.message);
    next(err);
  }
}

async function insertProduct(req, res, next) {
  try {
    res.json(await productService.insertProductToDb(req.body));
  } catch (err) {
    console.error(`Error while getting programming languages`, err.message);
    next(err);
  }
}

async function updateProduct(req, res, next) {
  try {
    res.json({method:"updateProduct" ,body:req.body});
  } catch (err) {
    console.error(`Error while getting programming languages`, err.message);
    next(err);
  }
}

async function deleteProduct(req, res, next) {
  try {
    res.json({method:"deleteProduct" ,body:req.body});
  } catch (err) {
    console.error(`Error while getting programming languages`, err.message);
    next(err);
  }
}

module.exports = {
  insertProduct,
  getAllProducts,
  getProduct,
  updateProduct,
  deleteProduct,
};