const mongoose = require('mongoose')
const TeacherFileSchema= new mongoose.Schema({
    title:{
        type:String,
        minlength:2,
        require:true
    },
    file:{
        type:String,
        require: true
    },
    createDate:{
        type:Date,
        default:Date.now(),
        require:true
    },
    classContainerId:{
        type:mongoose.Types.ObjectId,
        require:true,
        ref:"classContainer"
    },
    classId:{
        type:mongoose.Types.ObjectId,
        require:true,
        ref:"class"
    },
    teacherId:{
        type:mongoose.Types.ObjectId,
        require:true,
        ref:"teacher"
    },
    semesterName:{
        type:Number,
        required:true
    }
})
const TeacherFileModel = mongoose.model("teacherFile",TeacherFileSchema)
module.exports = TeacherFileModel
