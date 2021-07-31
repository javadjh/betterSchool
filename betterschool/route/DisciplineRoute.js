const express = require('express')
const {getSingleStudentDiscipline} = require("../handler/discipline/query/DisciplineQuery");
const {insertUsersDiscipline} = require("../handler/discipline/command/DisciplineCommand");
const {deputy} = require("../middlewares/AuthMiddleware");
const {needSemester} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/discipline',[needSemester,deputy] , insertUsersDiscipline)
router.get('/students/discipline/:id',[needSemester,deputy],getSingleStudentDiscipline)

module.exports = router
