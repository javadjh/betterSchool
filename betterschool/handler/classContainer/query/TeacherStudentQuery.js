const ClassContainerModel = require("../../../model/ClassContainer");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getClassesStudent=async (req,res)=>{
    let students = await ClassContainerModel.findOne({
        _id:req.params.id
    }).populate("students").select("students -_id").lean()

    students.students.map(s=>{
        s.createDate = convertToShamsi(s.createDate)
    })
    res.send(students)
}
