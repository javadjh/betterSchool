const ExamModel = require("../../../model/ExamModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {isValidObjectId} = require("mongoose")
module.exports.getExams = async (req,res)=>{
    let exams = await ExamModel.find({
        classId:req.params.id
    }).sort({"createDate":-1}).lean()



   exams.map(e=>{
        e.startDate = convertToShamsi(e.startDate)
        e.createDate = convertToShamsi(e.createDate)
       e.hasUpdated = e.students.length > 0;
    })
    res.send(exams)
}

module.exports.getSingleExam = async (req,res)=>{
    if(!isValidObjectId(req.params.id)) return res.status(400).send({"error":"ای دی مشکل دارد"})
    let singleExam = await ExamModel.findOne({
        _id:req.params.id
    })
    res.send(singleExam)
}

module.exports.getAllClassExam = async (req,res)=>{
    const exams = await ExamModel.find({
        classContainerId : req.params.id
    }).sort({"createDate":-1}).lean()

    exams.map(e=>{
        e.startDate = convertToShamsi(e.startDate)
        e.createDate = convertToShamsi(e.createDate)
        e.hasUpdated = e.students.length > 0;
    })

    res.send(exams)
}
