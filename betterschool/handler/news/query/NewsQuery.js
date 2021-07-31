const NewsModel = require("../../../model/NewsModel");
const {convertToShamsi} = require("../../../utility/dateUtility");
module.exports.getNews= async (req,res)=>{
    let {pageId,eachPerPage} = req.query
    pageId = parseInt(pageId)
    eachPerPage = parseInt(eachPerPage)
    let news = await NewsModel.find({
        semesterName:req.se
    }).limit(eachPerPage).skip((pageId-1)*eachPerPage).lean()
    news.map(n=>{
        n.createDate = convertToShamsi(n.createDate)
    })
    res.send({
        pageId,
        eachPerPage,
        news 
    })

}
