const TeacherFileModel = require("../../../model/TeacherFileModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getTeachersFile= async (req,res)=>{
    const {id} = req.params
    console.log(id)
    let teachersFile = await TeacherFileModel.find({
        semesterName:req.se,
        /*teacherId:req.user.userId,*/
        classId:id
    }).sort({createDate:-1}).lean()
    teachersFile.map(t=>{
        t.createDate=convertToShamsi(t.createDate)
    })
    res.send(teachersFile)
}
