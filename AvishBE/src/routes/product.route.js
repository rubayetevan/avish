const express = require('express');
const router = express.Router();

const productController = require('../controllers/product.controller');

router.get('/all', productController.getAllProducts);
router.get('/:id', productController.getProduct);
router.put('/', productController.updateProduct);
router.delete('/', productController.deleteProduct);
router.post('/', productController.insertProduct);


module.exports = router;