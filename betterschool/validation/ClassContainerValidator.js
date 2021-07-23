const Joi = require('joi')
Joi.objectId = require('joi-objectid')(Joi)

module.exports.insertClassContainerValidator = (data)=>{
    const validator = Joi.object({
        name:Joi.string().required(),
        students:Joi.required()
    })
    return validator.validate(data)
}
module.exports.updateClassContainerValidator = (data)=>{
    const validator = Joi.object({
        id:Joi.objectId().required(),
        classes:Joi.required()
    })
    return validator.validate(data)
}
