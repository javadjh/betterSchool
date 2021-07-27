const express = require("express")
const {getAllTeachers} = require("../handler/teacher/query/TeacherQuery");
const {getSingleTeacher} = require("../handler/teacher/query/TeacherQuery");
const {updateTeacher} = require("../handler/teacher/command/TeacherCommand");
const {deleteTeacher} = require("../handler/teacher/command/TeacherCommand");
const {insertTeacher} = require("../handler/teacher/command/TeacherCommand");
const {getTeachers} = require("../handler/teacher/query/TeacherQuery");
const {headmaster} = require("../middlewares/AuthMiddleware");
const route = express.Router()

route.get('/teachers',headmaster,getTeachers)
route.post('/insert/teacher',headmaster,insertTeacher)
route.delete('/teacher/:id',headmaster,deleteTeacher)
route.post('/update/teacher',headmaster,updateTeacher)
route.get('/teacher/:id',headmaster,getSingleTeacher)
route.get('/allteachers',headmaster,getAllTeachers)

module.exports = route
