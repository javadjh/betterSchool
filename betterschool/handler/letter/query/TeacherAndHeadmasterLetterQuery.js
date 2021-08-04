const StudentModel = require("../../../model/StudentModel");
const TeacherModel = require("../../../model/TeacherModel");
const LetterModel = require("../../../model/LetterModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getStudentAndTeacher = async (req,res)=>{
    const {type,lastName} = req.query
    let students;
    let teacher
    if(type==="student"){
        students = await StudentModel.find({
            grade:{
                $lte:3
            },
            lastName:new RegExp(lastName)
        }).select("name lastName").limit(20).lean()
    }else{
        teacher = await TeacherModel.find({
            lastName:new RegExp(lastName)
        }).select("name lastName title").limit(20).lean()
    }

    res.send({
        students,
        teacher
    })
}

module.exports.getUsersLetter = async (req,res)=>{
    let {filter} = req.params
    let letters
    if(filter==="public") {
        letters = await LetterModel.find({
            semesterName: req.se,
        }).or([
            {"type": req.user.department === "teacher" ? "AllTeacher" : req.user.department === "student"?"AllStudent":req.user.department==="headmaster"?new RegExp(""):res.status(400).send({"error":"مجوز ندارید"})}
        ]).select("title description senderInformation type seen createDate").sort({"createDate":-1}).lean()
    }else if(filter==="private"){
        letters = await LetterModel.find({
            semesterName: req.se,
            userId:req.user.userId
        }).select("title description senderInformation type seen createDate").sort({"createDate":-1}).lean()
    }else if(filter==="send"){
        letters = await LetterModel.find({
            semesterName: req.se,
            "senderInformation.userId":req.user.userId
        }).select("title description senderInformation type seen createDate").sort({"createDate":-1}).lean()
    }

    letters.map(l=>{
        l.createDate = convertToShamsi(l.createDate)
    })

    res.send(letters)
}

module.exports.getLetterSingle = async (req,res)=>{
    let letter = await LetterModel.findById(req.params.id);
    console.log(letter.senderInformation.userId!==req.user.userId )
    console.log( letter.userId!==req.user.userId)
    console.log(letter.type!=="AllStudent")
    if((letter.senderInformation.userId!==req.user.userId )&&( letter.userId!==req.user.userId )&& (letter.type!=="AllStudent") ) return res.status(400).send({"error":"مجوز ندارید"})
    letter.seen = true
    letter = await letter.save()
    let letterEdited = await LetterModel.findById(req.params.id).lean();
    letterEdited.createDate = convertToShamsi(letterEdited.createDate)
    res.send(letterEdited)
}
