require('dotenv').config();

const express = require('express');
const app = express();
app.use(express.json());

const connectMongoDB = require('./src/config/mongodb.config');
connectMongoDB();


const productRoute = require('./src/routes/product.route');
app.use('/api/product', productRoute)
const userRoute = require('./src/routes/user.route');
app.use('/api/user', userRoute)
const authRoute = require('./src/routes/auth.route');
app.use('/api/auth', authRoute)

const PORT = process.env.PORT || 3030;

app.listen(PORT, () => {
    console.log(`Server Started at ${PORT}`)
})