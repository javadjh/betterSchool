const LetterModel = require("../../../model/LetterModel");
const {insertLetterValidator} = require("../../../validation/LetterValidator");
module.exports.teachersInsertLetterCommand = async (req,res)=>{
    const {title,description,receiverInformation,type,userId} = req.body
    const {error} = insertLetterValidator({title,description,type})
    if (error) return res.status(400).send({"error":error.message})
    let newLetter = await new LetterModel({
        title,
        description,
        type,
        senderInformation:{
            name:req.user.name,
            lastName:req.user.lastName,
            userId:req.user.userId
        },
        semesterName:req.se,
        //Optional
        receiverInformation,
        userId,
    })

    if(!newLetter) return res.status(400).send({"error":"خطا در ثبت اطلعات رخ داده است"})

    await newLetter.save()
    res.send(true)
}
