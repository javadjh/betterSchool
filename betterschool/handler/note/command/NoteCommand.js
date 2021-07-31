const NoteModel = require("../../../model/NoteModel");
const {insertNoteValidator} = require("../../../validation/NoteValidator");
module.exports.insertNote = async (req,res)=>{
    const {title,description,classId} = req.body
    const {error} = insertNoteValidator(req.body)
    if(error) return  res.status(400).send({"error":error.message})
    const newNote = await new NoteModel({
        title,
        description,
        classId
    })
    if(!newNote) return res.status(400).send({"error":"خطا در ثبت اطلاعات رخ داده است"})
    await newNote.save()
    res.send(true)
}
