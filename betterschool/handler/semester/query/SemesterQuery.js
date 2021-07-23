const SemesterModel = require("../../../model/SemesterModel");
module.exports.getSemester = async (req,res)=>{
    try {
        const semesters = await SemesterModel.find().sort({name:-1})
        const currentSemester = semesters[0]
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
