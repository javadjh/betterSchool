const StudentModel = require("../../../model/StudentModel");
const {insertStudent} = require("../../../validation/StudentValidator");
const {isValidObjectId} = require('mongoose')
module.exports.insertStudent = async (req,res)=>{
    let {name,lastName,melliCode,fathersName,grade} = req.body
    const {error} = insertStudent(req.body)
    if(error) return res.status(400).send({"error":error.message})
    grade = parseInt(grade)
    let newStudent = await new StudentModel({
        name,
        lastName,
        melliCode,
        fathersName,
        grade
    })
    newStudent = await newStudent.save()
    res.send(newStudent)

}
module.exports.deleteStudent= async (req,res)=>{
    if(!isValidObjectId(req.params.id)) return res.status(400).send({"error":"اطلاعات وارد شده معتبر نمیباشد"})
    const studentDeleted = await StudentModel.findOneAndRemove({
        _id:req.params.id
    })
    if(!studentDeleted) return res.status(400).send({"error":"کاربر یافت نشد"})
    res.send(true)
}
