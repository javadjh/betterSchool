const ClassModel = require("../../../model/ClassModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getClasses = async (req,res)=>{
    const classes = await ClassModel.find({
        semesterName:req.se,
        classContainer:req.query.classContainer
    }).populate("teacher").lean()
    classes.map(c=>{
        console.log(c.createDate)
        c.createDate = convertToShamsi(c.createDate)

        /*!c.firstFinalExam==null ? convertToShamsi(c.firstFinalExam):undefined
        !c.firstExam==null ? convertToShamsi(c.firstExam):undefined
        !c.secondExam==null ? convertToShamsi(c.secondExam):undefined
        !c.secondFinalExam==null ? convertToShamsi(c.secondFinalExam):undefined*/

    })
    res.send(classes)
}
