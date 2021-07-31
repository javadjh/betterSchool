const NoteModel = require("../../../model/NoteModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getNotes = async (req,res)=>{
    const note = await NoteModel.find({
        classId:req.params.id
    }).sort({"createDate":-1}).lean()
    note.map(n=>{
        n.createDate = convertToShamsi(n.createDate)
    })
    res.send(note)
}
