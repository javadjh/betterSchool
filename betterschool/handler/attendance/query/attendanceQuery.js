const AttendanceModel = require("../../../model/AttendanceModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getAttendance = async (req,res)=>{
    let attendances = await AttendanceModel.find({
        classId:req.params.id
    }).lean().sort({"createDate":-1})

    attendances.map(a=>{
        a.createDate = convertToShamsi(a.createDate)
    })

    res.send(attendances)
}

module.exports.getSingleAttendance = async (req,res)=>{
    let attendance = await AttendanceModel.findOne({
        _id:req.params.id
    })
    res.send(attendance)
}
