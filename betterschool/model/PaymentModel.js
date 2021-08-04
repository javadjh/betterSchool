const mongoose = require('mongoose')

const PaymentSchema = new mongoose.Schema({
    type:{
        type:String,
        required:true,
    },
    payId:{
        type:String,
        required:true
    },
    semesterName:{
        type:Number,
        required:true
    },
    userId:{
        type:mongoose.Types.ObjectId,
        ref:"student",
        required:true
    },
    title:{
        type:String,
        required:true
    },
    createDate:{
        type:Date,
        default:Date.now,
        required:true
    }
})

const PaymentModel = mongoose.model("payment",PaymentSchema)
module.exports = PaymentModel
