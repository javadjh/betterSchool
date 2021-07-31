const Joi = require('joi')
Joi.objectId = require('joi-objectid')(Joi)

module.exports.insertTeacherFileValidator = (data)=>{
    const validator = Joi.object({
        title:Joi.string().required().min(2),
        classContainerId:Joi.objectId().required(),
        classId:Joi.objectId().required(),
    })
    return validator.validate(data)
}
