function getRandom(min, max, num) {
	var temp = Math.abs(max - min);
	var list = [];
	var tep1;
	var n = 0;
	while (n++ < num) {
		min <= max ? tep1 = Math.round(Math.random() * temp + min) : tep1 = Math.round(Math.random() * temp + max);
		list.push(tep1);
	}
	return list;
}

/**
 * 获取传入时间几天前的时间
 * @param {Object} days
 */
function get_calculate(date, days) {

	date.setTime(date.getTime() - days * 24 * 60 * 60 * 1000);
	var seperator = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	//生成十天前的时间格式。
	var sDate = year + seperator + month + seperator + strDate;
	return sDate
}


/**
 * 获取两个时间之间的每一天
 * @param {Object} start
 * @param {Object} end
 */
function formatEveryDay(start, end) {
	
    var dateList = []; 
    var startTime = getDate(start);
    var endTime = getDate(end);

    while ((endTime.getTime() - startTime.getTime()) >= 0) {
        var year = startTime.getFullYear();
        var month = startTime.getMonth() + 1 < 10 ? '0' + (startTime.getMonth() + 1) : startTime.getMonth() + 1;
        var day = startTime.getDate().toString().length == 1 ? "0" + startTime.getDate() : startTime.getDate();
        dateList.push(year + "-" + month + "-" + day); 
        startTime.setDate(startTime.getDate() + 1);
    }
    return dateList;
}


function getDate(datestr) {
    var temp = datestr.split("-");
    var date = new Date(temp[0], temp[1] - 1, temp[2]);
    return date;
}

//获取guid
function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}




