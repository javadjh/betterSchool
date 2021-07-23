const mongoose = require('mongoose')

const connectionMethod = ()=>{
    const connection = mongoose.connect("mongodb://localhost:27017/betterSchool",{
        useNewUrlParser: true,
        useUnifiedTopology:true
    })
    if(connection){
        console.log("connection database has running")
    }else{
        console.log("connection database has fail")
    }
}
module.exports = connectionMethod()
