const DisciplineModel = require("../../../model/DisciplineModel");
const DeputyNoteModel = require("../../../model/DeputyNoteModel");
module.exports.getSingleStudentDiscipline = async (req,res)=>{
    let {id} = req.params
    let getDisciplineScores = await DisciplineModel.findOne({
        studentId:id,
        semesterName:req.se
    })

    let notes = await DeputyNoteModel.find({
        studentId:id,
        semesterName:req.se
    }).populate("typeId").populate("studentId").sort({createDate:-1}).lean()

    let suggestionScore = 20

    notes.map(n=>{
        suggestionScore = suggestionScore-n.typeId.score
    })

    res.send({
        discipline:getDisciplineScores,
        notes,
        suggestionScore
    })
}
