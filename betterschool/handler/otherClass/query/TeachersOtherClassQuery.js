const OtherClassModel = require("../../../model/OtherClassModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {daysCalculate} = require("../../../utility/dateUtility");
module.exports.getTeachersOtherClass = async (req,res)=>{
    let otherClasses = await OtherClassModel.find({
        teacher:req.user.userId
    }).select("title image minParticipant maxParticipant startDate price teacher grade studentsId")
    .populate("teacher","name lastName -_id")
    .lean()

    otherClasses.map(o=>{
        o.defDate = daysCalculate(o.startDate,new Date)
        o.startDate = convertToShamsi(o.startDate)
        o.studentRegistered = o.studentsId.length
    })

    res.send(otherClasses)

}
