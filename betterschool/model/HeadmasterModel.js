const mongoose = require('mongoose')

const HeadmasterSchema = new mongoose.Schema({
    melliCode:{
        required:true,
        minlength:10,
        type:String
    },
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
    password:{
        required:true,
        type:String
    },
    createDate:{
        default:Date.now,
        type:Date
    }
})

const HeadmasterModel = mongoose.model("headmaster",HeadmasterSchema)
module.exports = HeadmasterModel
