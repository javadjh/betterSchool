const mongoose = require('mongoose')
const {StudentSchema} = require("./StudentModel");

const ExamSchema = new mongoose.Schema({
    title:{
        type:String,
        required:true,
        minlength:2
    },
    description:{
        type:String,
        required:true,
        minlength:2
    },
    createDate:{
        type:Date,
        default:Date.now,
        required:true
    },
    startDate:{
        type:Date,
        required:true
    },
    students:{
        type:[StudentSchema]
    },
    classId:{
        type:mongoose.Types.ObjectId,
        required:true
    },
    classContainerId:{
        type:mongoose.Types.ObjectId,
        required:true
    },
    type:{
        type:String,
        enum:["class","firstFinalExam","firstExam","secondFinalExam","secondExam"],
        required:true
    },
    semesterName:{
        type:Number,
        required:true
    }
})

const ExamModel = mongoose.model("exam",ExamSchema)
module.exports = ExamModel
