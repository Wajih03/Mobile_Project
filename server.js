const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();

const app = express();
const port = 5000; // You can choose any port

// Middleware
app.use(express.json());
app.use(cors()); // To allow cross-origin requests

// MongoDB connection
mongoose.connect('mongodb://localhost:27017/mydatabase', { useNewUrlParser: true, useUnifiedTopology: true })
    .then(() => console.log('MongoDB connected'))
    .catch(err => console.log(err));

// User Schema
const userSchema = new mongoose.Schema({
    name: String,
    email: String,
    username: String,
    password: String
});

const User = mongoose.model('User', userSchema);

// Signup endpoint
app.post('/signup', async (req, res) => {
    const { name, email, username, password } = req.body;
    const newUser = new User({ name, email, username, password });
    console.log('Received signup request:', req.body);
    try {
        await newUser.save();
        res.status(200).json({ message: 'User registered successfully' });
    } catch (error) {
        console.error('Error saving user to DB:', error);
        res.status(400).json({ error: error.message });
    }
    
});

// Login endpoint
app.post('/login', async (req, res) => {
    const { username, password } = req.body;
    console.log(`Login attempt with username: ${username}, password: ${password}`);

    try {
        const user = await User.findOne({ username, password });
        if (user) {
            res.status(200).json({ message: 'Login successful' });
        } else {
            res.status(400).json({ error: 'Invalid credentials' });
        }
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

// Start server
app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
});
