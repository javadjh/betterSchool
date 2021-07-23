const Joi = require('joi')
Joi.objectId = require('joi-objectid')(Joi)
module.exports.insertValidator = (data)=>{
    const validator = Joi.object({
        name:Joi.string().required(),
        teacher:Joi.objectId(),
        dayStart:Joi.string().required(),
        timeStart:Joi.string().required(),
        firstFinalExam:Joi.string(),
        secondFinalExam:Joi.string(),
        firstExam:Joi.string(),
        secondExam:Joi.string(),
        classContainer:Joi.objectId().required(),
    })
    return validator.validate(data)
}
