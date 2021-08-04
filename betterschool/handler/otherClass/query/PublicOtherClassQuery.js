const OtherClassModel = require("../../../model/OtherClassModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getSingleOtherClass = async (req,res)=>{
    let otherClass = await OtherClassModel.findById(req.params.id).populate("teacher","name lastName -_id").populate("studentsId","name lastName").lean()

    otherClass.canRegister = otherClass.startDate>new Date()
    otherClass.startDate = convertToShamsi(otherClass.startDate)
    otherClass.endDate = convertToShamsi(otherClass.endDate)

    if(otherClass.canRegister){
        if(otherClass.maxParticipant<=otherClass.studentsId.length){
            otherClass.canRegister = false
        }
    }

    res.send(otherClass)
}
