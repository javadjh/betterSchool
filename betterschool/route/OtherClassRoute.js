const multer  = require('multer')
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'uploads/')
    },
    filename: function (req, file, cb) {
        cb(null,  Date.now()+"-"+file.originalname)
    }
})
const upload = multer({ storage: storage })

const express = require('express')
const {teacher} = require("../middlewares/AuthMiddleware");
const {getTeachersOtherClass} = require("../handler/otherClass/query/TeachersOtherClassQuery");
const {joinOtherClass} = require("../handler/otherClass/command/StudentOtherClassCommand");
const {getSingleOtherClass} = require("../handler/otherClass/query/PublicOtherClassQuery");
const {student} = require("../middlewares/AuthMiddleware");
const {getStudentsOtherClass} = require("../handler/otherClass/query/StudentsOtherClassQuery");
const {getOtherClass} = require("../handler/otherClass/query/OtherClassQuery");
const {insertOtherClass} = require("../handler/otherClass/command/OtherClassCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const {needSemester} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/other/class',[needSemester,headmaster], upload.single("image") , insertOtherClass)
router.get('/other/classes/:id',[needSemester,headmaster] , getOtherClass)
//student
router.get('/student/other/classes',[needSemester,student] , getStudentsOtherClass)
router.put('/join/other/student/:id',[needSemester,student] , joinOtherClass)
//public
router.get('/other/class/:id',getSingleOtherClass)
//teacher
router.get('/teachers/other/classes',[needSemester,teacher],getTeachersOtherClass)


module.exports = router
