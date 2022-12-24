const { check, validationResult } = require("express-validator");

const userConstrains = [
    check("email")
      .isEmail()
      .withMessage("Invalid email address")
      .normalizeEmail(),
  ];

  function validateUser(req,res,next){
    const error = validationResult(req).formatWith(({ msg }) => msg);

    const hasError = !error.isEmpty();

    if (hasError) {
      res.status(422).json(error);
    } else {
      next();
    }
  }

  module.exports={userConstrains,validateUser}