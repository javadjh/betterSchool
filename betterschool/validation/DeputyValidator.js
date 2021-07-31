const Joi = require('joi')
Joi.objectId = require('joi-objectid')(Joi)

module.exports.insertDeputyValidator = (data)=>{
    const validator = Joi.object({
        studentId:Joi.objectId().required(),
        typeId:Joi.objectId().required(),
        description:Joi.string().required().min(2),
    })
    return validator.validate(data)
}
