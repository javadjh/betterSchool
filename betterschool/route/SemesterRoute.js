const express = require('express')
const {headmaster} = require("../middlewares/AuthMiddleware");
const {getSemester} = require("../handler/semester/query/SemesterQuery");
const {insertSemester} = require("../handler/semester/command/SemesterCommand");
const router = express.Router()

router.post('/insert/semester',headmaster,insertSemester)
router.get('/semesters',headmaster,getSemester)


module.exports = router
