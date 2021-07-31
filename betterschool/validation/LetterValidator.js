const Joi = require("joi")
Joi.objectId = require('joi-objectid')(Joi)
module.exports.insertLetterValidator=(data)=>{
    const validator = Joi.object({
        title:Joi.string().required().min(2),
        description:Joi.string().required().min(2),
        teacherSender:Joi.objectId().required(),
        headmasterSender:Joi.objectId(),
        studentSender:Joi.objectId(),
        parentSender:Joi.objectId(),
        headmasterReceiver:Joi.objectId(),
        teachersReceiver:Joi.objectId(),
        studentReceiver:Joi.objectId(),
        parentReceiver:Joi.objectId(),

        typeSender:Joi.string().required().valid(["student","teacher","parent","headmaster"]),
    })
    return validator.validate(data)
}
