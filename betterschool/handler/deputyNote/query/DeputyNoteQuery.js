const DeputyNoteModel = require("../../../model/DeputyNoteModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getDeputyNote = async (req,res)=>{
    let {pageId,eachPerPage,lastName} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    let deputyNote = await DeputyNoteModel.find({
        semesterName:req.se
    })
    .skip((pageId-1)*eachPerPage)
    .limit(eachPerPage)
    .populate("studentId","-password -createDate")
    .populate("typeId","-createData")
    .lean()

    deputyNote.map(d=>{
        d.createDate = convertToShamsi(d.createDate)
    })
    let finalList = deputyNote
    if(lastName!=null || lastName!=="") {
        finalList = []
        for (let i = 0; i < deputyNote.length; i++) {
            if (deputyNote[i].studentId.lastName.includes(lastName)) {
                finalList.push(deputyNote[i])
            }
        }
    }

    res.send({
        pageId,
        eachPerPage,
        deputyNote:finalList
    })
}
