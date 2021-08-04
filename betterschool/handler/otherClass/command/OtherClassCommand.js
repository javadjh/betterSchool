const OtherClassModel = require("../../../model/OtherClassModel");
const {insertOtherClassValidator} = require("../../../validation/OtherClassValidator");
module.exports.insertOtherClass = async (req,res)=>{
    const {error} = insertOtherClassValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    let {
        title,
        description,
        minParticipant,
        maxParticipant,
        startDate,
        endDate,
        price,
        sessionsCount,
        teacher,
        timeStart,
        grade
    } = req.body

    minParticipant = parseInt(minParticipant)
    maxParticipant = parseInt(maxParticipant)
    sessionsCount = parseInt(sessionsCount)
    grade = parseInt(grade)

    let newOtherClass = await new OtherClassModel({
        title,
        description,
        minParticipant,
        maxParticipant,
        startDate,
        endDate,
        price,
        sessionsCount,
        teacher,
        timeStart,
        grade,
        semesterName:req.se,
        image:req.file.filename,
    })

    if(!newOtherClass) return res.status(400).send({"error":"خطا در ثبت کلاس"})
    await newOtherClass.save()
    res.send(true)
}

module.exports.deleteOtherClass = async (req,res)=>{
    let {id} = req.params
    const otherClassDeleted = await OtherClassModel.findByIdAndRemove(id)
    if(!otherClassDeleted) return res.status(400).send({"error":"حذف با خطا مواجعه شد"})
    res.send(true)
}
