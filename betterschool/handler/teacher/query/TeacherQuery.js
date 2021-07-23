const TeacherModel = require("../../../model/TeacherModel");
const {getTeachersValidator} = require("../../../validation/TeacherValidator");
module.exports.getTeachers = async (req,res)=>{
    let {pageId,eachPerPage,lastName,melliCode} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    const {error} = getTeachersValidator(req.query)
    if(error) return res.status(400).send({"error":error.message})
    const teachers = await TeacherModel.find({
        lastName : new RegExp(lastName),
        melliCode : new RegExp(melliCode)
    }).skip((pageId-1)*eachPerPage).limit(eachPerPage)

    res.send({
        pageId,
        eachPerPage,
        teachers
    })

}
