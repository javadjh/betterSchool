const NewsModel = require("../../../model/NewsModel");
module.exports.insertNews = async (req,res)=>{
    const {title,description} = req.body

    let newNews = await new NewsModel({
        title,
        description,
        image:req.file===undefined ?"noimage.png":req.file.filename,
        semesterName:req.se
    })
    if(!newNews) return res.status(400).send({"error":"خطا در ثبت اطلاعات"})
    await newNews.save()
    res.send(true)
}
