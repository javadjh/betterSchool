const express = require('express')
const {getDetailSingleStudent} = require("../handler/student/query/StudentQuery");
const {getDeputiesStudent} = require("../handler/student/query/DeputyStudentQuery");
const {deputy} = require("../middlewares/AuthMiddleware");
const {student} = require("../middlewares/AuthMiddleware");
const {getStudentsStudentDetail} = require("../handler/student/query/StudentsStudentQuery");
const {getStudentDetail} = require("../handler/student/query/StudentTeacherQuery");
const {teacher} = require("../middlewares/AuthMiddleware");
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
route.get("/student/detail/single",[headmaster,needSemester],getDetailSingleStudent)
route.get("/student/detail",[teacher,needSemester],getStudentDetail)
//student
route.get("/student/dashboard",[student,needSemester],getStudentsStudentDetail)
//deputy
route.get('/deputy/students',[deputy],getDeputiesStudent)


module.exports = route
