const express = require('express')
const {deleteClass} = require("../handler/class/command/ClassCommand");
const {getClasses} = require("../handler/class/query/ClassQuery");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {insertClass} = require("../handler/class/command/ClassCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const route = express.Router()

route.post('/insert/class',[headmaster,needSemester],insertClass)
route.get('/classes',[headmaster,needSemester],getClasses)
route.delete('/class/:id',[headmaster],deleteClass)

module.exports = route
