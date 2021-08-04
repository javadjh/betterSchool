const express = require('express')
const {justSetUser} = require("../middlewares/AuthMiddleware");
const {getLetterSingle} = require("../handler/letter/query/TeacherAndHeadmasterLetterQuery");
const {getUsersLetter} = require("../handler/letter/query/TeacherAndHeadmasterLetterQuery");
const {getStudentAndTeacher} = require("../handler/letter/query/TeacherAndHeadmasterLetterQuery");
const {headmasterAndTeacher} = require("../middlewares/AuthMiddleware");
const {teachersInsertLetterCommand} = require("../handler/letter/command/TeacherLetterCommand");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {teacher} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/letter',[headmasterAndTeacher,needSemester],teachersInsertLetterCommand)
//teachers and headmaster
router.get('/students/teachers/filter',[headmasterAndTeacher,needSemester],getStudentAndTeacher)


router.get('/letters/:filter',[justSetUser,needSemester],getUsersLetter)
router.get('/letter/:id',justSetUser,getLetterSingle)

module.exports = router
