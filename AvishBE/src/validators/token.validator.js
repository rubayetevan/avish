const jwt = require('jsonwebtoken')
require('dotenv').config();

const verifyToken = (req, res, next) => {
    const authHeader = req.headers.token;
    if (authHeader) {
        const token = authHeader.split(" ")[1];
        jwt.verify(token, process.env.APP_SECRET, (err, user) => {
            if (err) {
                res.status(403).json({ errors: ["Token is not valid!"] })
            }
            else {
                req.user = user;
                next();
            }
        });
    } else {
        return res.status(401).json({ errors: ["You are not authenticated!"] });
    }
}

const verifyTokenAndAuthorization = (req, res, next) => {
    verifyToken(req, res, () => {
        if (req.user.isAdmin) {
            next();
        } else {
            res.status(401).json({ errors: ["You are not authenticated!"] });
        }
    });
}

const verifyRefreshToken = (req, res, next) => {
    const refreshToken = req.body.refreshToken;
    if (refreshToken) {
        jwt.verify(refreshToken, process.env.APP_SECRET, (err, user) => {
            if (err) {
                res.status(403).json({ errors: ["Token is not valid!"] })
            }
            else {
                if (user.userName === req.body.userName) {
                    next();
                } else {
                    return res.status(401).json({ errors: ["You are not authenticated!"] });
                }
            }
        });
    } else {
        return res.status(401).json({ errors: ["You are not authenticated!"] });
    }
}

module.exports = { verifyTokenAndAuthorization, verifyToken, verifyRefreshToken }