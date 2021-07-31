const Joi = require("joi")
Joi.objectId = require('joi-objectid')(Joi)
 module.exports.insertNoteValidator = (data)=>{
     const validator = Joi.object({
         title:Joi.string().required().min(3),
         description:Joi.string().required().min(3),
         classId:Joi.objectId().required()
     })
     return validator.validate(data)
 }
