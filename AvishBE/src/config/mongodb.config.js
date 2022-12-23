require('dotenv').config();
const mongoose = require('mongoose');
const mongoString = process.env.DATABASE_URL;

mongoose.connect(mongoString);
const database = mongoose.connection;

database.on('error', (error) => {
    console.log(error)
})

database.once('connected', () => {
    console.log('Database Connected');
})

const connectMongoDB = async () => {
    try {
        await mongoose.connect(
            mongoString,
            {
                useNewUrlParser: true
            }
        );
    } catch (err) {
        process.exit(1);
    }
};


module.exports = connectMongoDB;