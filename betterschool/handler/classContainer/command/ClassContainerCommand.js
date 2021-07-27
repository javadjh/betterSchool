const ClassContainerModel = require("../../../model/ClassContainer");
const {updateClassContainerValidator} = require("../../../validation/ClassContainerValidator");
const {insertClassContainerValidator} = require("../../../validation/ClassContainerValidator");
module.exports.insertClassContainer = async (req,res)=>{
    const {name,students} = req.body
    const {error} = insertClassContainerValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    let newClassContainer = await new ClassContainerModel({
        name,
        semesterName:req.se,
        students
    })
    if(!newClassContainer) return res.status(400).send({"error":"خطا در ثبت کلاس اصلی رخ داده است"})
    newClassContainer = await newClassContainer.save()
    res.send(true)
}

module.exports.updateClassContainer = async (req,res)=>{
    const {id,classes} = req.body
    const {error} = updateClassContainerValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    let updateClassContainer = await ClassContainerModel.findOne({
        _id:id
    })
    if(!updateClassContainer) return res.status(400).send({"error":"خطا در ثبت کلاس اصلی رخ داده است"})
    updateClassContainer.classes = classes
    updateClassContainer = await updateClassContainer.save()
    res.send(updateClassContainer)
}

module.exports.moveStudent= async (req,res)=>{
    try {
        let {id,studentId,classContainer} = req.body
        let hasStudent = await ClassContainerModel.findOne({
            _id: id,
            students:studentId

        })
        if(!hasStudent) return res.status(400).send({"error":"کاربر یافت نشد"})
        let studentsMoved = await ClassContainerModel.findOne({_id: id})
        studentsMoved.students = studentsMoved.students.filter(s => s._id!=studentId)

        let insertStudent = await ClassContainerModel.findOne({
            _id:classContainer
        })
        insertStudent.students.push(studentId)

        await studentsMoved.save()
        await insertStudent.save()

        res.send(true)
    }catch (err){
        console.log(err)
    }

}
