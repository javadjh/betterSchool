const mongoose = require('mongoose')
const DisciplineSchema = new mongoose.Schema({
    studentId:{
        type:mongoose.Types.ObjectId,
        required:true,
        ref:"student"
    },
    semesterName:{
        type:Number,
        required:true
    },
    firstScore:{
        type:Number,
    },
    secondScore:{
        type:Number,
    }
})

const DisciplineModel = mongoose.model("discipline",DisciplineSchema)
module.exports = DisciplineModel
