const TeacherModel = require("../../../model/TeacherModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {getTeachersValidator} = require("../../../validation/TeacherValidator");
const {isValidObjectId} = require('mongoose')
module.exports.getTeachers = async (req,res)=>{
    let {pageId,eachPerPage,lastName,melliCode} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    const {error} = getTeachersValidator(req.query)
    if(error) return res.status(400).send({"error":error.message})
    let teachers = await TeacherModel.find({
        lastName : new RegExp(lastName),
        melliCode : new RegExp(melliCode)
    }).skip((pageId-1)*eachPerPage).limit(eachPerPage).lean()
    teachers.map(t=>{
        t.createDate = convertToShamsi(t.createDate)
    })

    res.send({
        pageId,
        eachPerPage,
        teachers
    })

}

module.exports.getSingleTeacher= async (req,res)=>{
    const isIdValid = isValidObjectId(req.params.id)
    if(!isIdValid) return res.status(400).send({"error":"ای دی مشکل دارد"})
    const teacher = await TeacherModel.findOne({
        _id:req.params.id
    })
    if(!teacher) return res.status(400).send({"error":"معلم یافت نشد"})
    res.send(teacher)
}

module.exports.getAllTeachers = async (req,res)=>{
    let teachers = await TeacherModel.find().select("name lastName _id title").lean()
    res.send(teachers)
}
