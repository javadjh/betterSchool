const SemesterModel = require("../../../model/SemesterModel");
const StudentModel = require("../../../model/StudentModel");
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

    let levelUpStudents = await StudentModel.find().lean()
    levelUpStudents.map(l=> l.grade = l.grade + 1)
    console.log(levelUpStudents)
    await levelUpStudents.save
    newSemester = await newSemester.save()
    res.send(true)
}

