const mongoose = require('mongoose')

const ClassSchema = new mongoose.Schema({
    name:{
        required:true,
        minlength:2,
        type:String
    },
    teacher:{
        type:mongoose.Types.ObjectId,
        required:true,
        ref:"teacher"
    },
    createDate:{
        type:Date,
        default:Date.now
    },
    semesterName:{
        type:Number,
        required:true
    },
    dayStart:{
        type:Number,
        required:true
    },
    timeStart:{
        type:String,
        required:true
    },
    firstFinalExam:{
        type:Date
    },
    secondFinalExam:{
        type:Date
    },
    firstExam:{
        type:Date
    },
    secondExam:{
        type:Date
    },
    classContainer:{
        type:mongoose.Types.ObjectId,
        required:true,
        ref:"classContainer"
    }
})

const ClassModel = mongoose.model("class",ClassSchema)
module.exports = ClassModel
