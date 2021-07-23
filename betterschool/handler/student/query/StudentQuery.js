const StudentModel = require("../../../model/StudentModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {getStudent} = require("../../../validation/StudentValidator");

module.exports.getStudents = async (req,res)=>{
    let {pageId,eachPerPage,melliCode,lastName} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    const {error} = getStudent(req.query)
    if(error) return res.status(400).send({"error":error.message})
    const students = await StudentModel.find({
        melliCode:new RegExp(melliCode),
        lastName:new RegExp(lastName)
    }).skip((pageId-1)*eachPerPage).limit(eachPerPage).select("-__v -password").lean()

    students.map(s=>{
        s.createDate = convertToShamsi(s.createDate)
    })
    res.send({
        pageId,
        eachPerPage,
        students
    })
}
