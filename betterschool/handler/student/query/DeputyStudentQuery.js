const StudentModel = require("../../../model/StudentModel");
module.exports.getDeputiesStudent = async (req,res)=>{
    let {pageId,eachPerPage,lastName} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    let students = await StudentModel.find({
        lastName:new RegExp(lastName),
        grade:{
            $lte:3
        }
    })
    .skip((pageId-1)*eachPerPage)
    .limit(eachPerPage)
    .select("name lastName grade")
    .lean()

    res.send({
        pageId,
        eachPerPage,
        students
    })
}
