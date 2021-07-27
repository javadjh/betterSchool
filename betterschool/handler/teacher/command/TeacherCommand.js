const TeacherModel = require("../../../model/TeacherModel");
const {updateTeacherValidator} = require("../../../validation/TeacherValidator");
const {insertTeacherValidator} = require("../../../validation/TeacherValidator");
const {isValidObjectId} = require('mongoose')
module.exports.insertTeacher= async (req,res)=>{
    let {name,lastName,title,melliCode} = req.body
    const {error} = insertTeacherValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
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

module.exports.updateTeacher = async (req,res)=>{
    const {id,name,lastName,melliCode,title} = req.body
    const {error} = updateTeacherValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    const teacherUpdated = await TeacherModel.findOneAndUpdate({
        _id:id
    },{
        $set:{
            name,
            lastName,
            melliCode,
            title
        }
    })
    if(!teacherUpdated) return res.status(400).send({"error":"خطا در ویرایش معلم"})
    res.send(true)
}

