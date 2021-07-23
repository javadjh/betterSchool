const jwt = require('jsonwebtoken')
module.exports.headmaster= async (req,res,next)=>{

    if(!req.headers.token) return res.status(401).send({"error":"شما دسترسی به این بخش را ندارید"})
    const isTokenValid = await jwt.verify(req.headers.token,"sdc65s4dcSDCD$(3sd1c23s5c416s5c1s61c65c3scs3csc631s6cDCSDCS")
    if(!isTokenValid) return  res.status(401).send({"error":"شما دسترسی به این بخش را ندارید"})
    const tokenDecoded = await jwt.decode(req.headers.token)
    if(tokenDecoded.department==="headmaster")
        next()
    else
        return  res.status(401).send({"error":"شما دسترسی به این بخش را ندارید"})
}

module.exports.needSemester = (req,res,next)=>{
    if(!req.headers.semester) return res.status(400).send({"error":"لطفا نیم اسل تحصیلی را انتخاب کنید"})
    req.se = req.headers.semester
    console.log(req.se)
    next()
}
