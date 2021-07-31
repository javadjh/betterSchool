const StudentModel = require("../../../model/StudentModel");
const ClassContainerModel = require("../../../model/ClassContainer");
const {convertToShamsi} = require("../../../utility/dateUtility");
const {getStudent} = require("../../../validation/StudentValidator");
const _ = require('lodash')

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


