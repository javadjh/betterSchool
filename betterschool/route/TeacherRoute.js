const express = require("express")
const {deleteTeacher} = require("../handler/teacher/command/TeacherCommand");
const {insertTeacher} = require("../handler/teacher/command/TeacherCommand");
const {getTeachers} = require("../handler/teacher/query/TeacherQuery");
const {headmaster} = require("../middlewares/AuthMiddleware");
const route = express.Router()

route.get('/teachers',headmaster,getTeachers)
route.post('/insert/teacher',headmaster,insertTeacher)
route.delete('/teacher/:id',headmaster,deleteTeacher)

module.exports = route
