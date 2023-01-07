const express = require('express');
const router = express.Router();

const authController = require('../controllers/auth.controller');
const tokenValidator = require('../validators/token.validator');

router.post('/login', authController.login);
router.post('/refreshToken',tokenValidator.verifyRefreshToken, authController.getNewToken);

module.exports = router;