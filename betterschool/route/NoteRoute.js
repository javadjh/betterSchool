const express = require('express')
const {insertNote} = require("../handler/note/command/NoteCommand");
const {getNotes} = require("../handler/note/query/NoteQuery");
const {teacher} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.get('/notes/:id',[teacher],getNotes)
router.post('/insert/note',[teacher],insertNote)

module.exports = router
