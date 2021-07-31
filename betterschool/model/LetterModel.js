const mongoose = require('mongoose')
const LetterSchema = new mongoose.Schema({
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

    typeSender:{
        type:String,
        required:true
    },


    teacherSender:{
        type:mongoose.Types.ObjectId,
        ref:"teacher"
    },
    headmasterSender:{
        type:mongoose.Types.ObjectId,
        ref:"headmaster"
    },
    studentSender:{
        type:mongoose.Types.ObjectId,
        ref:"student"
    },
    parentSender:{
        type:mongoose.Types.ObjectId,
        ref:"parent"
    },


    headmasterReceiver:{
        type:Boolean,
    },
    teachersReceiver:{
        type:[mongoose.Types.ObjectId],
        ref:"teacher"
    },
    studentReceiver:{
        type:[mongoose.Types.ObjectId],
        ref:"student"
    },
    parentReceiver:{
        type:[mongoose.Types.ObjectId],
        ref:"parent"
    },


    seen:{
        type:Boolean,
        default:false
    },
    createDate:{
        type:Date,
        default:Date.now
    },
    semesterName:{
        type:Number,
        required:true
    }
})
const LetterModel = mongoose.model("letter",LetterSchema)
module.exports = LetterModel
