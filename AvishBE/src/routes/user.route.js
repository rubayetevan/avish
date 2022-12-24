const express = require('express');
const router = express.Router();

const userController = require('../controllers/user.controller');
const userValidator = require('../validators/user.validator');

router.post('/register',userValidator.userConstrains,userValidator.validateUser, userController.registerUser);

module.exports = router;