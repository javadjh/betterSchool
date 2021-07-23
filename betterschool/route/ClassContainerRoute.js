const express = require('express')
const {getClassContainers} = require("../handler/classContainer/query/ClassContainerQuery");
const {updateClassContainer} = require("../handler/classContainer/command/ClassContainerCommand");
const {insertClassContainer} = require("../handler/classContainer/command/ClassContainerCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const {needSemester} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/classcontainer',[needSemester,headmaster],insertClassContainer)
router.post('/update/classcontainer',[needSemester,headmaster],updateClassContainer)
router.get('/classcontainers',[needSemester],getClassContainers)


module.exports = router
