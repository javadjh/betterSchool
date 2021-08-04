const ClassModel = require("../../../model/ClassModel");
const ClassContainerModel = require("../../../model/ClassContainer");
module.exports.getStudentsClass = async (req,res)=>{
    let classContainerId = await ClassContainerModel.findOne({
        students:req.user.userId,
        semesterName:parseInt(req.se)
    }).select("_id").lean()

    let classes = await ClassModel.find({
        classContainer:classContainerId._id
    }).sort({"dayStart":1}).populate("teacher")

    res.send(classes)
}
