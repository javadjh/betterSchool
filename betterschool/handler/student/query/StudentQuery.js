const StudentModel = require("../../../model/StudentModel");
const ClassContainerModel = require("../../../model/ClassContainer");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {getStudent} = require("../../../validation/StudentValidator");
const _ = require('lodash')
const ExamModel = require("../../../model/ExamModel");
const PaymentModel = require("../../../model/PaymentModel");
const OtherClassModel = require("../../../model/OtherClassModel");
const AttendanceModel = require("../../../model/AttendanceModel");
const DeputyNoteModel = require("../../../model/DeputyNoteModel");
const DisciplineModel = require("../../../model/DisciplineModel");

module.exports.getStudents = async (req,res)=>{
    let {pageId,eachPerPage,melliCode,lastName} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    const {error} = getStudent(req.query)
    if(error) return res.status(400).send({"error":error.message})
    const students = await StudentModel.find({
        melliCode:new RegExp(melliCode),
        lastName:new RegExp(lastName)
    }).skip((pageId-1)*eachPerPage).limit(eachPerPage).select("-__v -password").lean()

    students.map(s=>{
        s.createDate = convertToShamsi(s.createDate)
    })
    res.send({
        pageId,
        eachPerPage,
        students
    })
}

module.exports.getAllStudents = async (req,res)=>{
    let {grade} = req.query
    grade = parseInt(grade)
    let students = await StudentModel.find({
        grade
    }).select("name lastName _id grade").lean()

    let usersRegistered = await ClassContainerModel.find({
        semesterName:req.se,
    }).populate("students","name lastName _id grade").select("students -_id").lean()


    let list = []
    for (let i = 0 ; i<usersRegistered.length ; i++){
        if(usersRegistered[i].students!=null){
            for (let j = 0 ; j<usersRegistered[i].students.length ;j++){
                if(usersRegistered[i].students[j].grade===grade)
                    list.push(usersRegistered[i].students[j])
            }
        }
    }
    // console.log(students.filter(x => !list.includes(x)))
    let resultB = students.filter(elm => !list.map(elm => JSON.stringify(elm)).includes(JSON.stringify(elm)));

    console.log(resultB)
    res.send(resultB)
    // res.send(students)
}

module.exports.getDetailSingleStudent = async (req,res)=>{
    let {studentId} = req.query
    //get students
    let student = await StudentModel.findById(studentId).select("-password").lean()
    student.createDate = convertToShamsi(student.createDate)

    //get students class
    let studentsClassContainer = await ClassContainerModel.findOne({
        students:studentId,
        semesterName:req.se
    }).populate("classes").lean()


    console.log(student.melliCode)
    //get Exams
    let studentsExam = await ExamModel.find({
        // semesterName:req.se,
        students:{
            $elemMatch:{
                melliCode:student.melliCode
            }
        }
    }).lean()
    let finalScoreList = []
    for (let i = 0 ; i<studentsExam.length ; i++){
        finalScoreList.push({
            title : studentsExam[i].title,
            description : studentsExam[i].description,
            startDate : convertToShamsi(studentsExam[i].startDate),
            type : studentsExam[i].type
        })
        for (let j = 0 ; j<studentsExam[i].students.length ; j++){
            if(studentsExam[i].students[j].melliCode==student.melliCode){
                finalScoreList[i].student = {
                    fathersName:studentsExam[i].students[j]._id,
                    grade:studentsExam[i].students[j].grade,
                    lastName:studentsExam[i].students[j].lastName,
                    melliCode:studentsExam[i].students[j].melliCode,
                    name:studentsExam[i].students[j].name,
                    score:studentsExam[i].students[j].score,
                }
            }
        }
    }

    //get payment
    let payments = await PaymentModel.find({
        userId:studentId,
        semesterName:req.se
    }).lean()

    //get other Class
    let studentsOtherClass = await OtherClassModel.find({
        studentsId:studentId,
        semesterName:req.se
    }).lean()

    //get Attendance
    let attendance = await AttendanceModel.find({
        // semesterName:req.se,
        students:{
            $elemMatch:{
                melliCode:student.melliCode
            }
        }
    }).lean()
    let finalAttendanceList = []
    for (let i = 0 ; i<attendance.length ; i++){
        for (let j = 0 ; j<attendance[i].students.length ; j++){
            if(!attendance[i].students[j].isPresent){
                finalAttendanceList.push(attendance[i].students[j])
            }
        }
    }


    //get deputy Note
    let studentsDeputyNotes = await DeputyNoteModel.find({
        studentId,
        semesterName:req.se
    }).populate("typeId").lean()

    //get Discipline
    let studentsDiscipline = await DisciplineModel.findOne({
        studentId,
        semesterName:req.se
    }).lean()


    res.send({
        student,
        classContainers:studentsClassContainer,
        exams:finalScoreList,
        payments,
        otherClasses:studentsOtherClass,
        attendances:finalAttendanceList,
        deputyNotes:studentsDeputyNotes,
        disciplines:studentsDiscipline
    })


}

