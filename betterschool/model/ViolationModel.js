const mongoose = require('mongoose')
const ViolationSchema = new mongoose.Schema({
    score:{
        type:Number,
        required:true,
        default:0
    },
    createData:{
        type:Date,
        default:Date.now,
        required:true,
    },
    title:{
        type:String,
        required:true,
        minlength:2
    }
})

const ViolationModel = mongoose.model("violation",ViolationSchema)
module.exports = ViolationModel
