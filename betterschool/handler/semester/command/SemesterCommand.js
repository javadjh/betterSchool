const SemesterModel = require("../../../model/SemesterModel");
module.exports.insertSemester= async (req,res)=>{
    let allSemester = await SemesterModel.find().sort({ name : -1}).lean()
    let newSemester
    if(allSemester.length===0) {
        newSemester = await new SemesterModel({
            name:1
        })
    }else{
        newSemester = await new SemesterModel({
            name:allSemester.length+1
        })
    }

    newSemester = await newSemester.save()
    res.send(newSemester)
}

