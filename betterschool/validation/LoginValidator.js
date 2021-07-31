const Joi = require('joi')

module.exports.loginValidator = (data)=>{
    const validator = Joi.object({
        melliCode:Joi.string().required().min(10),
        password:Joi.string().required().min(8),
        department:Joi.string().required().valid("student","headmaster","teacher","moderator","deputy")
    })
    return validator.validate(data)
}
