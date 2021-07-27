const express = require('express')
const {needSemester} = require("../middlewares/AuthMiddleware");
const {getAllStudents} = require("../handler/student/query/StudentQuery");
const {deleteStudent} = require("../handler/student/command/StudentCommand");
const {insertStudent} = require("../handler/student/command/StudentCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const {getStudents} = require("../handler/student/query/StudentQuery");
const route = express.Router()

route.get("/students",headmaster,getStudents)
route.post("/insert/student",headmaster,insertStudent)
route.delete("/student/:id",headmaster,deleteStudent)
route.get("/allstudent",[headmaster,needSemester],getAllStudents)


module.exports = route
