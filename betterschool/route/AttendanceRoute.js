const express = require('express')
const {getSingleAttendance} = require("../handler/attendance/query/attendanceQuery");
const {getAttendance} = require("../handler/attendance/query/attendanceQuery");
const {insertAttendance} = require("../handler/attendance/command/AttendanceCommand");
const {teacher} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/attendance',[teacher],insertAttendance)
router.get('/attendances/:id',[teacher],getAttendance)
router.get('/attendance/:id',[teacher],getSingleAttendance)

module.exports = router
