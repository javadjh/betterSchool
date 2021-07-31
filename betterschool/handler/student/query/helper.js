const ExamModel = require("../../../model/ExamModel");
const AttendanceModel = require("../../../model/AttendanceModel");
const TeacherFileModel = require("../../../model/TeacherFileModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getStudentDetailLogic  = async (classId, id,res,isStudentDashboard ,req)=>{
    let getScores = await ExamModel.find({
        classId,
        students:{
            $elemMatch:{
                _id:id
            }
        }
    }).lean()

    let finalScoreList = []
    for (let i = 0 ; i<getScores.length ; i++){
        finalScoreList.push({
            title : getScores[i].title,
            description : getScores[i].description,
            startDate : convertToShamsi(getScores[i].startDate),
            type : getScores[i].type
        })
        for (let j = 0 ; j<getScores[i].students.length ; j++){
            if(getScores[i].students[j]._id==id){
                finalScoreList[i].student = {
                    fathersName:getScores[i].students[j]._id,
                    grade:getScores[i].students[j].grade,
                    lastName:getScores[i].students[j].lastName,
                    melliCode:getScores[i].students[j].melliCode,
                    name:getScores[i].students[j].name,
                    score:getScores[i].students[j].score,
                }
            }
        }
    }


    let getAttendance = await AttendanceModel.find({
        classId,
        students:{
            $elemMatch:{
                _id:id,
                isPresent:false
            }
        }
    }).lean()

    let finalAttendanceList = []
    for (let i = 0 ; i<getAttendance.length ; i++){
        finalAttendanceList.push({
            createDate : convertToShamsi(getAttendance[i].createDate),
        })
        for (let j = 0 ; j<getAttendance[i].students.length ; j++){
            if(getAttendance[i].students[j]._id==id){
                finalAttendanceList[i].student = {
                    lastName:getAttendance[i].students[j].lastName,
                    name:getAttendance[i].students[j].name,
                }
            }
        }
    }
    console.log("second")
    if(!isStudentDashboard) {
        console.log("first")
        res.send({
            scores: finalScoreList,
            attendance: finalAttendanceList
        })
    }else{
        console.log("second")
        let files = await TeacherFileModel.find({
            classId,
            semesterName:req.se,
        }).sort({createDate:-1}).lean()
        files.map(f=>{
            f.createDate = convertToShamsi(f.createDate)
        })

        res.send({
            scores: finalScoreList,
            attendance: finalAttendanceList,
            files
        })

    }
}
