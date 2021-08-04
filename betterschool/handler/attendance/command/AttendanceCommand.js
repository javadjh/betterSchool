const AttendanceModel = require("../../../model/AttendanceModel");
module.exports.insertAttendance = async (req,res)=>{
    let {students,classId,classContainerId} = req.body
    let newAttendance = await new AttendanceModel({
        students,
        classId,
        classContainerId,
        semesterName:req.se
    })
    if(!newAttendance) return res.status(400).send({"error":"ثبت حضور غیاب بات مشکل مواجعه شد"})

    newAttendance = await newAttendance.save()
    res.send(true)
}
