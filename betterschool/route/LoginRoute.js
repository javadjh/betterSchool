const express = require('express')
const {login} = require("../handler/Login");
const route = express.Router()

route.post("/login",login)

module.exports = route
