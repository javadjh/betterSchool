const HeadmasterModel = require("../model/HeadmasterModel");
const {loginValidator} = require("../validation/LoginValidator");
const bcrypt = require('bcrypt')
const {genToken} = require("../utility/jwtUtility");
module.exports.login = async (req,res)=>{
    let {melliCode,password,department} = req.body
    const {error} = loginValidator(req.body)
    if(error) return res.status(400).send({"error":error.message})
    let user
    if(department==="headmaster"){
        user = await HeadmasterModel.findOne({
            melliCode
        }).lean()
    }
    if(!user) return res.status(400).send({"error":"نام کاربری و یا رمز عبور اشتبار میباشد"})
    const isPasswordValid = await bcrypt.compare(password,user.password)
    if(!isPasswordValid) return res.status(400).send({"error":"نام کاربری و یا رمز عبور اشتبار میباشد"})
    res.send(genToken({
        name:user.name,
        lastName:user.lastName,
        department
    }))
}
