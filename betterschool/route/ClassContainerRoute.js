const express = require('express')
const {getClassesStudent} = require("../handler/classContainer/query/TeacherStudentQuery");
const {teacher} = require("../middlewares/AuthMiddleware");
const {moveStudent} = require("../handler/classContainer/command/ClassContainerCommand");
const {getMoveStudentSingle} = require("../handler/classContainer/query/ClassContainerQuery");
const {getSingleClassContainers} = require("../handler/classContainer/query/ClassContainerQuery");
const {getClassContainers} = require("../handler/classContainer/query/ClassContainerQuery");
const {updateClassContainer} = require("../handler/classContainer/command/ClassContainerCommand");
const {insertClassContainer} = require("../handler/classContainer/command/ClassContainerCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const {needSemester} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/classcontainer',[needSemester,headmaster],insertClassContainer)
router.post('/update/classcontainer',[needSemester,headmaster],updateClassContainer)
router.get('/classcontainers',[needSemester],getClassContainers)
router.get('/classcontainer',[needSemester],getSingleClassContainers)
router.get('/move/student/:id',[headmaster,needSemester],getMoveStudentSingle)
router.post('/move/student',[headmaster],moveStudent)

//teachers
router.get("/teachers/classes/:id",[teacher],getClassesStudent)


module.exports = router
