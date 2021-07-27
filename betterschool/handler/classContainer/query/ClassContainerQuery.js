const ClassContainerModel = require("../../../model/ClassContainer");
const ClassModel = require("../../../model/ClassModel");
const StudentModel = require("../../../model/StudentModel");
module.exports.getClassContainers= async (req,res)=>{
    let classContainers = await ClassContainerModel.find({
        semesterName:req.se
    }).populate("classes","-students")
    res.send(classContainers)
}

module.exports.getSingleClassContainers = async (req,res)=>{
    let classContainer = await ClassContainerModel.findOne({
        _id:req.query.id
    }).populate("students","name lastName _id").populate("classes").lean()

    res.send(classContainer)
}

module.exports.getMoveStudentSingle = async (req,res)=>{
    let classContainer = await ClassContainerModel.find({
        semesterName:req.se
    }).select("name")

    let students = await ClassContainerModel.findOne({
        semesterName:parseInt(req.se),
        _id:req.params.id
    }).select("students -_id").populate("students")

    res.send({
        classContainer,
        students,
    })
}

