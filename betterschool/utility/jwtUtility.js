const jwt = require('jsonwebtoken')

module.exports.genToken = (data)=>{
    return {token:jwt.sign(data, "sdc65s4dcSDCD$(3sd1c23s5c416s5c1s61c65c3scs3csc631s6cDCSDCS")}
}
