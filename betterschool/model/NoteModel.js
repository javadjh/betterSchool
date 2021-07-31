const mongoose = require('mongoose')

const NoteSchema = new mongoose.Schema({
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
    classId:{
        type:mongoose.Types.ObjectId,
        required:true
    }
})

const NoteModel = mongoose.model("note",NoteSchema)
module.exports = NoteModel

