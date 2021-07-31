const express = require('express')
const {getDeputyNote} = require("../handler/deputyNote/query/DeputyNoteQuery");
const {insertDeputyNote} = require("../handler/deputyNote/command/DeputyNoteCommand");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {deputy} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/note/deputy',[deputy,needSemester] , insertDeputyNote )
router.get('/note/deputies',[deputy,needSemester] , getDeputyNote )

module.exports = router
