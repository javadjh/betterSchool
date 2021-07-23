const TeacherModel = require("../../../model/TeacherModel");
const {isValidObjectId} = require('mongoose')
module.exports.insertTeacher= async (req,res)=>{
    let {name,lastName,title,melliCode} = req.body
    let newTeacher = await new TeacherModel({
        name,
        lastName,
        title,
        melliCode
    })
    newTeacher = await newTeacher.save()
    res.send(newTeacher)
}

module.exports.deleteTeacher = async (req,res)=>{
    if(!isValidObjectId(req.params.id)) return res.status(400).send({"error":"اطلاعات وارد شده معتبر نمیباشد"})
    const deletedTeacher = await TeacherModel.findOneAndRemove({
        _id:req.params.id
    })
    if(!deletedTeacher) return res.status(400).send({"error":"کاربر یافت نشد"})
    res.send(true)
}
