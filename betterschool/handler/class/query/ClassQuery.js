const ClassModel = require("../../../model/ClassModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getClasses = async (req,res)=>{
    const classes = await ClassModel.find({
        semesterName:req.se
    }).populate("teacher").lean()
    classes.map(c=>{
        console.log(c.createDate)
        c.createDate = convertToShamsi(c.createDate)
    })
    res.send(classes)
}
