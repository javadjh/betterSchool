const mongoose = require('mongoose')

const DeputyNoteSchema = new mongoose.Schema({
    createDate:{
        type:Date,
        default:Date.now,
        required:true
    },
    studentId:{
        type:mongoose.Types.ObjectId,
        required:true,
        ref:"student"
    },
    typeId:{
        type:mongoose.Types.ObjectId,
        ref:"violation",
        required:true
    },
    description:{
        type:String,
        required:true,
        minlength:2
    },
    semesterName:{
        type:Number,
        required:true
    }
})

const DeputyNoteModel = mongoose.model("deputyNote",DeputyNoteSchema)
module.exports = DeputyNoteModel
