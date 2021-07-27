const ClassModel = require("../../../model/ClassModel");
module.exports.getClassesTeacher = async (req,res)=>{
    console.log(req.user)
    let classes = await ClassModel.find({
        teacher:req.user.userId,
        semesterName:req.se,
    }).populate("classContainer","-students -classes").populate("classContainer.classes")
        .populate("teacher","-password -melliCode -createDate -__v")
        .select("-firstExam -secondExam -firstFinalExam -secondFinalExam").lean().populate({
        path: 'classContainer.classes',
        populate: { path: 'class' }
    });

    res.send(classes)
}
