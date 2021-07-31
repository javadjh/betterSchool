const {getStudentDetailLogic} = require("./helper");
const {isValidObjectId} = require('mongoose')
module.exports.getStudentsStudentDetail = async (req,res)=> {
    let {classId, id} = req.query
    if (!isValidObjectId(id) || !isValidObjectId(classId)) return res.status(400).send({"error": "ای دی مشکل دارد"})
    await getStudentDetailLogic(classId, req.user.userId, res,true,req)
}


