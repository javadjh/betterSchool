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
const {getTeachersFile} = require("../handler/teacherFile/query/TeacherFileQuery");
const {insertTeacherFile} = require("../handler/teacherFile/command/TeacherFileCommand");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {teacher} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/teacher/file',[teacher,needSemester],upload.single("file"),insertTeacherFile)
router.get('/teachers/file/:id',[teacher,needSemester],getTeachersFile)


module.exports = router
