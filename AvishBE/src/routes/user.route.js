const express = require('express');
const router = express.Router();

const userController = require('../controllers/user.controller');
const userValidator = require('../validators/user.validator');
const tokenValidator = require('../validators/token.validator');

router.post('/register', userValidator.userConstrains, userValidator.validateUser,
    userController.registerUser);

router.put('/update', userValidator.userConstrains, userValidator.validateUser,tokenValidator.verifyTokenAndAuthorization,
    userController.updateUser);

module.exports = router;