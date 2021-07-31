const ViolationModel = require("../../../model/ViolationModel");
module.exports.getViolations = async (req,res)=>{
    let violations = await ViolationModel.find().select("-__v -createData").lean()
    res.send(violations)
}
