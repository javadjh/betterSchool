const OtherClassModel = require("../../../model/OtherClassModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {daysCalculate} = require("../../../utility/dateUtility");
module.exports.getStudentsOtherClass = async (req,res)=>{
    const studentsOtherClass = await OtherClassModel.find({
        semesterName:req.se,
        grade:req.user.grade,
        studentsId:req.user.userId
    }).select("title image minParticipant maxParticipant startDate price teacher grade studentsId")
    .populate("teacher","name lastName -_id")
    .lean()

    studentsOtherClass.map(o=>{
        o.defDate = daysCalculate(o.startDate,new Date)
        o.startDate = convertToShamsi(o.startDate)
        o.studentRegistered = o.studentsId.length
    })

    const otherClass = await OtherClassModel.find({
        semesterName:req.se,
        grade:req.user.grade,
    }).select("title image minParticipant maxParticipant startDate price teacher grade studentsId")
        .populate("teacher","name lastName -_id")
        .lean()
    otherClass.map(o=>{
        o.defDate = daysCalculate(o.startDate,new Date)
        o.startDate = convertToShamsi(o.startDate)
        o.studentRegistered = o.studentsId.length
    })

    let finalOtherClass = otherClass.filter(elm => !studentsOtherClass.map(elm => JSON.stringify(elm)).includes(JSON.stringify(elm)));


    res.send({
        otherClassRegistered:studentsOtherClass,
        allOtherClasses:finalOtherClass
    })

}
