const express = require('express')
/*const {getTeacherSingleLetter} = require("../handler/letter/query/LetterQuery");
const {getSingleLetter} = require("../handler/letter/query/LetterQuery");
const {getTeachersLetters} = require("../handler/letter/query/LetterQuery");
const {insertTeacherLetter} = require("../handler/letter/command/LetterCommand");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {teacher} = require("../middlewares/AuthMiddleware");*/
const router = express.Router()

//teacher
/*router.post("/insert/letter",[teacher,needSemester] , insertTeacherLetter)
router.get("/teachers/letter",[teacher,needSemester] , getTeachersLetters)
router.get("/teachers/letter/:id",[teacher] , getTeacherSingleLetter)*/


module.exports = router
