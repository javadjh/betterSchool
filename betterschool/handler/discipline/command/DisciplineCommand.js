const DisciplineModel = require("../../../model/DisciplineModel");
module.exports.insertUsersDiscipline = async (req,res)=>{
    const {firstScore,secondScore,studentId} = req.body
    if(firstScore==null && secondScore==null) return res.status(400).send({"error":"لطفا نمره را وارد کنید"})

    let hasFirstScore = await DisciplineModel.findOne({
        semesterName:req.se,
        studentId
    })
    if(hasFirstScore){
        let setSecondScore = await DisciplineModel.findOneAndUpdate({
            _id:hasFirstScore._id,
        },{
            $set:{
                secondScore
            }
        })
    }else{
        let setFirstScore = await new DisciplineModel({
            studentId,
            semesterName:req.se,
            firstScore
        })
        await setFirstScore.save()
    }
    res.send(true)
}
