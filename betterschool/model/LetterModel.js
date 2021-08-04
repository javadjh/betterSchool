const mongoose = require('mongoose')

const UserInformation = new mongoose.Schema({
    name:{
        required:true,
        type:String
    },
    lastName:{
        required:true,
        type:String
    },
    userId:{
        type:mongoose.Types.ObjectId,
        required:true
    }
})

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
    userId:{
        type:mongoose.Types.ObjectId,
    },
    senderInformation:{
        type:UserInformation,
    },
    receiverInformation:{
        type:UserInformation
    },
    type:{
        type:String,
        required:true
    },
    file:{
        type:String
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
    },

})
const LetterModel = mongoose.model("letter",LetterSchema)
module.exports = LetterModel
