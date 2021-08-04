const PaymentModel = require("../../../model/PaymentModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getUsersPayment = async (req,res)=>{
    let payments = await PaymentModel.find({
        userId:req.user.userId,
        semesterName:req.se
    }).lean()

    payments.map(p=>{
        p.createDate = convertToShamsi(p.createDate)
    })

    res.send(payments)
}
