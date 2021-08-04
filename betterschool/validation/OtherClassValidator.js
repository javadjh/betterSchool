const Joi = require('joi')
Joi.objectId = require('joi-objectid')(Joi)

module.exports.insertOtherClassValidator = (data) =>{
    const validator = Joi.object({
        title:Joi.string().required().min(3),
        description:Joi.string().required().min(11),
        minParticipant:Joi.number().required(),
        maxParticipant:Joi.number().required(),
        startDate:Joi.date().required(),
        endDate:Joi.date().required(),
        price:Joi.number().required(),
        sessionsCount:Joi.number().required(),
        teacher:Joi.objectId().required(),
        timeStart:Joi.string(),
        grade:Joi.number().required()
    })

    return validator.validate(data)
}
