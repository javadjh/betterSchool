const Joi = require('joi')
module.exports.insertViolationValidator = (data)=>{
    const validator = Joi.object({
        title:Joi.string().required().min(2),
        score:Joi.number().required(),
    })
    return validator.validate(data)
}
