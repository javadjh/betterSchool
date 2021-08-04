const OtherClassModel = require("../../../model/OtherClassModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {daysCalculate} = require("../../../utility/dateUtility");
module.exports.getOtherClass = async (req,res)=>{
    const {id} = req.params
    let otherClasses = await OtherClassModel.find({
        semesterName:req.se,
        grade:id
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
