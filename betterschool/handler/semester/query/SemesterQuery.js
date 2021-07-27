const SemesterModel = require("../../../model/SemesterModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getSemester = async (req,res)=>{
    try {
        let semesters = await SemesterModel.find().sort({name:-1}).lean()
        const currentSemester = semesters[0]
        semesters.map(s=>{
            s.createDate = convertToShamsi(s.createDate)
        })
        res.send({
            currentSemester:currentSemester.name,
            semesters
        })
    }catch (err){
        res.send({
            currentSemester:0,
            semesters:[]
        })
    }
}
