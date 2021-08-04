const ExamModel = require("../../../model/ExamModel");
const {insertExamValidator} = require("../../../validation/ExamValidator");
module.exports.insertExam = async (req,res)=>{
    const {title,description,startDate,students,classId,classContainerId,type} = req.body
    const {error} = insertExamValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})

    let newExam = await new ExamModel({
        title,
        description,
        startDate,
        students,
        classId,
        classContainerId,
        type,
        semesterName:req.se
    })
    await newExam.save()
    res.send(true)

}

module.exports.setStudentsExamScore = async (req,res)=>{
    const {id,students} = req.body
    let examUpdated = await ExamModel.findOne({
        _id:id
    })
    examUpdated.students = students
    await examUpdated.save()
    res.send(true)
}
