const express = require('express')
const {getViolations} = require("../handler/violation/query/ViolationQuery");
const {insertViolation} = require("../handler/violation/command/ViolationCommand");
const {deputy} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/violation',[deputy] , insertViolation)
router.get('/violations',[deputy] , getViolations)


module.exports = router
