const mongoose = require('mongoose')

const ClassContainerSchema = new mongoose.Schema({
    name:{
        type:String,
        minlength:2,
        required:true
    },
    classes:{
        type:[mongoose.Types.ObjectId],
        ref:"class"
    },
    semesterName:{
        type:Number,
        required:true
    },
    students:{
        type:[mongoose.Types.ObjectId],
        required:true,
        ref:"student"
    },
})

const ClassContainerModel = mongoose.model("classContainer",ClassContainerSchema)

module.exports = ClassContainerModel
