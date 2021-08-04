const OtherClassModel = require("../../../model/OtherClassModel");
const PaymentModel = require("../../../model/PaymentModel");
module.exports.joinOtherClass = async (req,res)=>{
    let getOtherClass = await OtherClassModel.findOne({_id:req.params.id})

    if(req.user.department!=="student") return res.status(400).send({"error":"شما دانش آموز نیستید"})
    if(getOtherClass.studentsId!==undefined)
        if(getOtherClass.studentsId.includes(req.user.userId)) return res.status(400).send({"error":"شما قبلا ثبت نام کردید"})
    if(getOtherClass.startDate<new Date()) return res.status(400).send({"error":"تاریخ گذشته است "})
    if(getOtherClass.grade != req.user.grade) return res.status(400).send({"error":"این کلاس برای دوره ی شما نمیباشد"})

    getOtherClass.studentsId.push(req.user.userId)

    let setPayment = await new PaymentModel({
        type:"otherClass",
        payId:"5456451212d854d52d4csd",
        semesterName:req.se,
        userId:req.user.userId,
        title:getOtherClass.title
    })
    await setPayment.save()
    await getOtherClass.save()
    res.send(true)
}
