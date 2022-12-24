const userService = require('../services/user.service')

// async function getAllProducts(req, res, next) {
//   try {
//     res.json(await productService.getAllProductFromDb());
//   } catch (err) {
//     console.error(`Error while getting programming languages`, err.message);
//     next(err);
//   }
// }

// async function getProduct(req, res, next) {
//   try {
//     res.json({method:"getProduct",id:req.params.id});
//   } catch (err) {
//     console.error(`Error while getting programming languages`, err.message);
//     next(err);
//   }
// }

async function registerUser(req, res, next) {
  try {
    const result = await userService.registerUser(req.body);
    if("errors" in result){
      res.status(409).json(result);
    }else{
      res.status(201).json(result);
    }
    
  } catch (err) {
    console.error(`Error while registering user`, err.message);
    next(err);
  }
}

// async function updateProduct(req, res, next) {
//   try {
//     res.json({method:"updateProduct" ,body:req.body});
//   } catch (err) {
//     console.error(`Error while getting programming languages`, err.message);
//     next(err);
//   }
// }

// async function deleteProduct(req, res, next) {
//   try {
//     res.json({method:"deleteProduct" ,body:req.body});
//   } catch (err) {
//     console.error(`Error while getting programming languages`, err.message);
//     next(err);
//   }
// }

module.exports = {
  registerUser
};