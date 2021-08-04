const mongoose = require('mongoose')
const {StudentSchema} = require("./StudentModel");

const AttendanceSchema = mongoose.Schema({
    students:{
        type:[StudentSchema],
        require:true
    },
    createDate:{
        type: Date,
        require: true,
        default:Date.now()
    },
    classId:{
        type:mongoose.Types.ObjectId,
        ref:"class",
        require:true
    },
    classContainerId:{
        type:mongoose.Types.ObjectId,
        ref:"classContainer",
        require:true
    },
    semesterName:{
        type:Number,
        require:true
    }
})

const AttendanceModel = mongoose.model("attendance",AttendanceSchema)
module.exports = AttendanceModel;

