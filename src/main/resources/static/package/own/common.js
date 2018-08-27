/*
    一、js目录
		1.layui验证扩展方法
		2.生成html方法
		3.js获取当前时间
		4.js获取当前日期
 */

/*
 *layui表单验证
 */
layui.form.verify({
	tagValue: function(value, item){
		if(!/^(0|[1-9]+[0-9]*)(px|%)?$/.test(value)){
			return '输入数据格式不对';
		}
	}
})

/*
 * js生成html
 * tagHtml--输出全部的html代码
 * pageName--输出页面默认名字
 */
var generateHTML = function(tagHtml, pageName){
	var firstPartHtml = '<!DOCTYPE html>' +
		'<html>                       ' +
		'<head>                       ' +
		'	<meta charset="utf-8">    ' +
		'	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">  ' +
		'	<title> ' + pageName + ' </title>  ' +
		'	<link rel="stylesheet" href="../package/layui-2.2.5/css/layui.css">  ' +
		'</head>                      ';
		
	var secondPartHtml = '</body> </html> ';
	var wholePageHtml = firstPartHtml + tagHtml + secondPartHtml;
	//导出代码	
	var urlObject = window.URL || window.webkitURL || window; 
    var export_blob = new Blob([wholePageHtml]);
    var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
    save_link.href = urlObject.createObjectURL(export_blob);
    save_link.download = pageName;
	var ev = document.createEvent("MouseEvents");
    ev.initMouseEvent(
        "click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null
    );
    save_link.dispatchEvent(ev);
}

/*
 获取当前时间
 seperator1日期分割符号
 seperator2时间分割符号
 */
var getNowTime = function(seperator1, seperator2){
	var nowDate = new Date();
	return nowDate.getFullYear() + seperator1 + (nowDate.getMonth() + 1) + seperator1 + nowDate.getDate() +
		   " " + 
		   nowDate.getHours() + seperator2 + nowDate.getMinutes() + seperator2 + nowDate.getSeconds();
}

/*
 获取当前日期
 seperator1日期分割符号
 */
var getNowDate = function(seperator1){
	var nowDate = new Date();
	return nowDate.getFullYear() + seperator1 + (nowDate.getMonth() + 1) + seperator1 + nowDate.getDate();
}
