const mongoose = require('mongoose')

const SemesterSchema = new mongoose.Schema({
    name:{
        type:Number,
        required:true
    },
    createDate:{
        default:Date.now,
        type:Date
    },
})

const SemesterModel = mongoose.model("semester",SemesterSchema)

module.exports = SemesterModel
