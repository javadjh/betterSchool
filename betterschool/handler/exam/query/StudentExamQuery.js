const ClassContainerModel = require("../../../model/ClassContainer");
const ClassModel = require("../../../model/ClassModel");
const ExamModel = require("../../../model/ExamModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getStudentsExam = async (req,res)=>{
    let studentsClassContainer = await ClassContainerModel.findOne({
        semesterName:req.se,
        students:req.user.userId
    }).lean()

    const cc = studentsClassContainer._id
    let classes = await ClassModel.find({
        semesterName:parseInt(req.se),
        classContainer:cc
    }).lean()

    listFirstFinalExam = []
    listSecondFinalExam = []
    listFirstExam = []
    listSecondExam = []
    for (let i = 0 ; i<classes.length ; i++){
        if(classes[i].firstFinalExam!=null) {
            listFirstFinalExam.push({
                date: convertToShamsi(classes[i].firstFinalExam),
                name: classes[i].name,
            })
        }
        if(classes[i].secondFinalExam!=null) {
            listSecondFinalExam.push({
                date: convertToShamsi(classes[i].secondFinalExam),
                name: classes[i].name,
            })
        }
        if(classes[i].firstExam!=null) {
            listFirstExam.push({
                date: convertToShamsi(classes[i].firstExam),
                name: classes[i].name,
            })
        }
        if(classes[i].secondExam!=null) {
            listSecondExam.push({
                date: convertToShamsi(classes[i].secondExam),
                name: classes[i].name,
            })
        }
    }

    let getScores = await ExamModel.find({
        classContainerId:cc
    }).sort({createDate:-1}).lean()


    let finalScoreList = []
    for (let i = 0 ; i<getScores.length ; i++){
        finalScoreList.push({
            title : getScores[i].title,
            description : getScores[i].description,
            startDate : convertToShamsi(getScores[i].startDate),
            type : getScores[i].type
        })
        for (let j = 0 ; j<getScores[i].students.length ; j++){
            if(getScores[i].students[j]._id==req.user.userId){
                finalScoreList[i].student = {
                    id:getScores[i].students[j]._id,
                    grade:getScores[i].students[j].grade,
                    lastName:getScores[i].students[j].lastName,
                    melliCode:getScores[i].students[j].melliCode,
                    name:getScores[i].students[j].name,
                    score:getScores[i].students[j].score,
                }
            }
        }
    }


    res.send({
        listFirstFinalExam,
        listSecondFinalExam,
        listFirstExam,
        listSecondExam,
        exams:finalScoreList
    })


}
