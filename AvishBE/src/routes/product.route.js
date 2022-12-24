const express = require('express');
const router = express.Router();

const productController = require('../controllers/product.controller');
const tokenValidator = require('../validators/token.validator');

router.get('/all', productController.getAllProducts);
router.get('/:id', productController.getProduct);
router.put('/',tokenValidator.verifyTokenAndAuthorization, productController.updateProduct);
router.delete('/',tokenValidator.verifyTokenAndAuthorization, productController.deleteProduct);
router.post('/',tokenValidator.verifyTokenAndAuthorization, productController.insertProduct);


module.exports = router;