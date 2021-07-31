const express = require('express')
const {getStudentsClass} = require("../handler/class/query/StudentClassQuery");
const {student} = require("../middlewares/AuthMiddleware");
const {getClassesTeacher} = require("../handler/class/query/TeacherClassQuery");
const {teacher} = require("../middlewares/AuthMiddleware");
const {setClassExam} = require("../handler/class/command/ClassCommand");
const {deleteClass} = require("../handler/class/command/ClassCommand");
const {getClasses} = require("../handler/class/query/ClassQuery");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {insertClass} = require("../handler/class/command/ClassCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const route = express.Router()

//students Access
route.get('/students/classes',[student,needSemester] , getStudentsClass )


//teacher Access
route.get('/teachers/classes',[teacher,needSemester],getClassesTeacher)

route.post('/insert/class',[headmaster,needSemester],insertClass)
route.get('/classes',[headmaster,needSemester],getClasses)
route.delete('/class/:id',[headmaster],deleteClass)
route.post('/class/exam',[headmaster],setClassExam)



module.exports = route
