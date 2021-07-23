const Joi = require('joi')

module.exports.getStudent = (data)=>{
    const validation = Joi.object({
        pageId:Joi.string().required(),
        eachPerPage:Joi.string().required(),
        lastName:Joi.string(),
        name:Joi.string(),
    })
    return validation.validate(data)
}
module.exports.insertStudent = (data)=>{
    const validation = Joi.object({
        name:Joi.string().required().min(2),
        lastName:Joi.string().required().min(2),
        melliCode:Joi.string().required().min(10).max(15),
        fathersName:Joi.string().required().min(2),
        grade:Joi.string().required(),
    })
    return validation.validate(data)
}
