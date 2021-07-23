const mongoose = require('mongoose')

const TeacherSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minlength: 2
    },
    lastName: {
        type: String,
        required: true,
        minlength: 2
    },
    title: {
        type: String,
        required: true,
        minlength: 2
    },
    password: {
        type:String,
    },
    melliCode:{
        type: String,
        required: true,
        minlength: 10
    },
    createDate:{
        default:Date.now,
        type:Date
    },
})

const TeacherModel = mongoose.model("teacher",TeacherSchema)
module.exports = TeacherModel
