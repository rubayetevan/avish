const authService = require('../services/auth.service')


async function login(req, res, next) {
  try {
    const result = await authService.login(req.body);
    if("errorMessage" in result){
      res.status(401).json(result);
    }else{
      res.status(200).json(result);
    }
    
  } catch (err) {
    console.error(`Error while login user`, err.message);
    next(err);
  }
}


async function getNewToken(req, res, next) {
  try {
    const result = await authService.getNewToken(req.body);
    if("errorMessage" in result){
      res.status(401).json(result);
    }else{
      res.status(200).json(result);
    }
    
  } catch (err) {
    console.error(`Error while login user`, err.message);
    next(err);
  }
}



module.exports = {
  login,getNewToken
};