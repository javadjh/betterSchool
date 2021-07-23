const ClassModel = require("../../../model/ClassModel");
const ClassContainerModel = require("../../../model/ClassContainer");
const {insertValidator} = require("../../../validation/ClassValidator");
module.exports.insertClass = async (req,res)=>{
    let {name,teacher,dayStart,timeStart,classContainer} = req.body
    const {error} = insertValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    let newClass = await new ClassModel({
        name,
        teacher,
        semesterName:req.se,
        dayStart,
        timeStart,
        classContainer
    })
    newClass = await newClass.save()
    if(!newClass) return  res.status(400).send({"error":"خطا در ثبت کلاس"})

    let setNewClassToClassContainer = await ClassContainerModel.findOne({
        _id:classContainer
    })
    setNewClassToClassContainer.classes.push(newClass._id)
    setNewClassToClassContainer = await setNewClassToClassContainer.save()

    res.send({
        classContainer:newClass,
        setNewClassToClassContainer
    })
}

module.exports.deleteClass = async (req,res)=>{
    let deletedClass = await ClassModel.findOneAndRemove({
        _id:req.params.id
    })
    if(!deletedClass) return res.status(400).send({"error":"کلاس یافت نشد"})
    res.send(true)
}
