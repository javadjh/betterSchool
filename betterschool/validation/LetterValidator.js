const Joi = require('joi')

module.exports.insertLetterValidator = (data)=>{
    const validator = Joi.object({
        title:Joi.string().required().min(2),
        description:Joi.string().required().min(2),
        type:Joi.string().required().valid("AllTeacher","AllStudent","Student","Teacher")
    })
    return validator.validate(data)
}
