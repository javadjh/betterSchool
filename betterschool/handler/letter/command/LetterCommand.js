const LetterModel = require("../../../model/LetterModel");
const {insertLetterValidator} = require("../../../validation/LetterValidator");
module.exports.insertLetter=async (req,res)=>{
    const {error} = insertLetterValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    const {
        title,
        description,
        teacherSender,
        headmasterSender,
        studentSender,
        parentSender,
        headmasterReceiver,
        teachersReceiver,
        studentReceiver,
        parentReceiver,
        typeSender
    } = req.body
    let doc={}
    if(typeSender==="teacher"){
        doc={
            title,
            description,
            teacherSender:req.user.userId,
            semesterName:req.se,
            typeSender,
            teachersReceiver
        }
    }else if(typeSender==="student"){
        doc={
            title,
            description,
            studentSender,
            typeSender,
            studentReceiver
        }
    }
    let newLetter = await new LetterModel({
        title,
        description,
        teacherSender,
        headmasterReceiver,
        semesterName:req.se,
        type:req.user.department
    })
    if(!newLetter) res.status(400).send({"error":"خطا در ثبت اطلاعات رخ داد"})
    await newLetter.save()
    res.send(true)
}
