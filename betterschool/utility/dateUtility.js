const moment = require('jalali-moment')

module.exports.convertToShamsi = (date)=>{
    return moment(date, 'YYYY-MM-DD').locale('fa').format('YYYY-MM-DD')
}
module.exports.daysCalculate = (date1, date2)=> {

    // The number of milliseconds in one day
    const ONE_DAY = 1000 * 60 * 60 * 24;

    // Calculate the difference in milliseconds
    const differenceMs = Math.abs(date1 - date2);

    // Convert back to days and return
    return Math.round(differenceMs / ONE_DAY);

}
