const ClassContainerModel = require("../../../model/ClassContainer");
module.exports.getStudentsClassContainer = async (req,res)=>{
    let classContainer = await ClassContainerModel.findOne({
        students:req.user.userId,
        semesterName:parseInt(req.se)
    }).populate("classes","name dayStart timeStart").lean()
    res.send(classContainer)
}
