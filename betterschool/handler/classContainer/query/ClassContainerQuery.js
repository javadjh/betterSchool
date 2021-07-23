const ClassContainerModel = require("../../../model/ClassContainer");
module.exports.getClassContainers= async (req,res)=>{
    let classContainers = await ClassContainerModel.find({
        semesterName:req.se
    }).populate("classes","-students")
    res.send(classContainers)
}
