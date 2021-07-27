const Joi = require('joi')
Joi.objectId = require('joi-objectid')(Joi)
module.exports.getTeachersValidator=(data)=>{
    const validator = Joi.object({
        pageId:Joi.string().required(),
        eachPerPage:Joi.string().required(),
        lastName:Joi.string(),
        melliCode:Joi.string(),
    })
    return validator.validate(data)
}
module.exports.insertTeacherValidator=(data)=>{
    const validator = Joi.object({
        name:Joi.string().required().min(2),
        lastName:Joi.string().required().min(2),
        title:Joi.string().required().min(2),
        melliCode:Joi.string().required().min(2),
    })
    return validator.validate(data)
}

module.exports.updateTeacherValidator=(data)=>{
    const validator = Joi.object({
        id:Joi.objectId().required(),
        name:Joi.string().required().min(2),
        lastName:Joi.string().required().min(2),
        title:Joi.string().required().min(2),
        melliCode:Joi.string().required().min(2),
    })
    return validator.validate(data)
}
