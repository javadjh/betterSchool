const Joi = require("joi")
Joi.objectId = require('joi-objectid')(Joi)

module.exports.insertExamValidator = (data)=>{
    const validator = Joi.object({
        title:Joi.string().min(2).required(),
        description:Joi.string().min(2).required(),
        startDate:Joi.required(),
        classId:Joi.objectId().required(),
        classContainerId:Joi.objectId().required(),
        type:Joi.string().required().valid("class","firstFinalExam","firstExam","secondFinalExam","secondExam")
    })
    return validator.validate(data)
}
