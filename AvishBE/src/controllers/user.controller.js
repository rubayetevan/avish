const userService = require('../services/user.service')


async function updateUser(req, res, next) {
  try {
    const result = await userService.updateUser(req.body);
    if("userName" in result){
       return res.status(200).json(result);
    }else{
      res.status(400).json(result);
    }
  } catch (err) {
    console.error(`Error while registering user`, err.message);
    next(err);
  }
}

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



module.exports = {
  registerUser,updateUser
};