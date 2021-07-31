const mongoose = require('mongoose')
const NewsSchema = new mongoose.Schema({
    title:{
        type:String,
        minlength:2,
        required:true
    },
    description:{
        type:String,
        minlength:2,
        required:true
    },
    image:{
        type:String,
        minlength:5,
        required:true
    },
    createDate:{
        type:Date,
        default:Date.now,
        required:true
    },
    semesterName:{
        type:Number,
        required:true
    }
})
const NewsModel = mongoose.model("news",NewsSchema)
module.exports = NewsModel
