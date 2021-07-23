const Joi = require('joi')

module.exports.getTeachersValidator=(data)=>{
    const validator = Joi.object({
        pageId:Joi.string().required(),
        eachPerPage:Joi.string().required(),
        lastName:Joi.string(),
        melliCode:Joi.string(),
    })
    return validator.validate(data)
}
