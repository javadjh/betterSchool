const express = require("express")
const {needSemester} = require("../middlewares/AuthMiddleware");
const {student} = require("../middlewares/AuthMiddleware");
const {getStudentsExam} = require("../handler/exam/query/StudentExamQuery");
const {getAllClassExam} = require("../handler/exam/query/ExamQuery");
const {getSingleExam} = require("../handler/exam/query/ExamQuery");
const {setStudentsExamScore} = require("../handler/exam/command/ExamCommand");
const {getExams} = require("../handler/exam/query/ExamQuery");
const {insertExam} = require("../handler/exam/command/ExamCommand");
const {teacher} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post("/insert/exam",[teacher] , insertExam)
router.post("/update/exam",[teacher] , setStudentsExamScore)
router.get("/exams/:id",[teacher] , getExams)
router.get("/exam/:id",[teacher] , getSingleExam)
router.get("/exams/class/:id",[teacher] , getAllClassExam)
//student
router.get("/students/exam",[student,needSemester] , getStudentsExam)



module.exports = router
