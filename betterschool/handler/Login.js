const HeadmasterModel = require("../model/HeadmasterModel");
const {loginValidator} = require("../validation/LoginValidator");
const bcrypt = require('bcrypt')
const TeacherModel = require("../model/TeacherModel");
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
    }else if(department==="teacher"){
        user = await TeacherModel.findOne({
            melliCode
        }).lean()
        if(user && user.password==null){
            console.log("gennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn")
            const salt = await bcrypt.genSalt(10)
            const passwordGenerated = await bcrypt.hash(password,salt)
            const setTeacherPassword = await TeacherModel.findOneAndUpdate({
                _id:user._id
            },{
                $set:{
                    password:passwordGenerated
                }
            })
            if(!setTeacherPassword) return res.status(400).send("خطا در ثبت پسورد")
            return res.send(genToken({
                name:user.name,
                lastName:user.lastName,
                department,
                userId:user._id
            }))
        }
    }


    if(!user) return res.status(400).send({"error":"نام کاربری و یا رمز عبور اشتبار میباشد"})
    const isPasswordValid = await bcrypt.compare(password,user.password)
    if(!isPasswordValid) return res.status(400).send({"error":"نام کاربری و یا رمز عبور اشتبار میباشد"})
    res.send(genToken({
        name:user.name,
        lastName:user.lastName,
        department,
        userId:user._id
    }))
}
