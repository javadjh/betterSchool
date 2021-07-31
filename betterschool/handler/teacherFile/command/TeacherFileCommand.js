const TeacherFileModel = require("../../../model/TeacherFileModel");
const {insertTeacherFileValidator} = require("../../../validation/TeacherFileValidator");
module.exports.insertTeacherFile = async (req,res)=>{
    let {title,classContainerId,classId} = req.body
    const {error} = insertTeacherFileValidator({title,classContainerId,classId})
    if(error) return res.status(400).send({"error":error.message})
    let newTeacherFile = await new TeacherFileModel({
        title,
        classContainerId,
        classId,
        file:req.file.filename,
        teacherId:req.user.userId,
        semesterName:req.se,
    })
    if(!newTeacherFile) return res.status(400).send({"error":"خطا در ثبت اطلاعات"})
    await newTeacherFile.save()

    res.send(true)
}
