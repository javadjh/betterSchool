const DeputyNoteModel = require("../../../model/DeputyNoteModel");
const {insertDeputyValidator} = require("../../../validation/DeputyValidator");
module.exports.insertDeputyNote = async (req,res)=>{
    const {studentId,typeId,description} = req.body
    const {error} = insertDeputyValidator(req.body)

    if(error) return res.status(400).send({"error":error.message})

    let newDeputyNote = await new DeputyNoteModel({
        studentId,
        typeId,
        description,
        semesterName:req.se
    })
    if(!newDeputyNote) return res.status(400).send({"error":"خطا در ثبت یادداشت"})
    await newDeputyNote.save()

    res.send(true)
}
