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
const {getNews} = require("../handler/news/query/NewsQuery");
const {needSemester} = require("../middlewares/AuthMiddleware");
const {insertNews} = require("../handler/news/command/NewsCommand");
const {headmaster} = require("../middlewares/AuthMiddleware");
const router = express.Router()

router.post('/insert/news',[headmaster,needSemester],upload.single("imageUrl"),insertNews)
router.get('/news',[needSemester],getNews)

module.exports = router
