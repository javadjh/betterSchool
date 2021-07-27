const mongoose = require('mongoose')

const StudentSchema = new mongoose.Schema({
    name:{
        required:true,
        minlength:2,
        type:String
    },
    lastName:{
        required:true,
        minlength:2,
        type:String
    },
    melliCode:{
        required:true,
        minlength:10,
        maxlength:11,
        type:String
    },
    fathersName:{
        required:true,
        minlength:2,
        type:String
    },
    password:{
        type:String
    },
    createDate:{
        default:Date.now,
        type:Date
    },
    grade:{
        required:true,
        type:Number
    },

    //for Attendance
    isPresent:{
        type:Boolean
    },
    hasNegativeScore:{
        type:Boolean
    },
    hasPositiveScore:{
        type:Boolean
    },

    //for exam
    score:{
        type:String
    }
})

const StudentModel = mongoose.model("student",StudentSchema)

module.exports = StudentModel
module.exports.StudentSchema = StudentSchema
