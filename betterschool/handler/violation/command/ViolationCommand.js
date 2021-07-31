const ViolationModel = require("../../../model/ViolationModel");
const {insertViolationValidator} = require("../../../validation/ViolationValidator");
module.exports.insertViolation = async (req,res)=>{
    let {score,title} = req.body
    const {error} = insertViolationValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})

    let newViolation = await new ViolationModel({
        title,
        score
    })
    if(!newViolation) return res.status(400).send({"error":"خطا در ثبت اطلاعات رخ داد"})
    await newViolation.save()
    res.send(true)

}
