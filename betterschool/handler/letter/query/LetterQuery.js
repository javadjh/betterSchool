const LetterModel = require("../../../model/LetterModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getTeachersLetters = async (req,res)=>{
    let filter
    if(req.query.type==="send"){
        filter={
            teacherSender:req.user.userId,
            semesterName:req.se
        }
    }else if(req.query.type==="receive"){
        filter={
            semesterName:req.se,
            teachersReceiver:req.user.userId
        }
    }else{
        return res.status(400).send({"error":"لطفا نوع را مشخص کنید"})
    }
    let letters = await LetterModel.find(filter).select("title seen description createDate").lean()
    letters.map(l=>{
        l.createDate = convertToShamsi(l.createDate)
    })
    res.send(letters)

}

module.exports.getTeacherSingleLetter = async (req,res)=>{
    let setSeen = await LetterModel.findOneAndUpdate({
        _id:req.params.id
    },{
        $set:{
            seen:true
        }
    },{new:true}).populate("teacherSender","name lastName").lean()

    setSeen.createDate = convertToShamsi(setSeen.createDate)
    if(!setSeen) return res.status(400).send({"error":"پیدا نشد"})
    res.send(setSeen)
}
