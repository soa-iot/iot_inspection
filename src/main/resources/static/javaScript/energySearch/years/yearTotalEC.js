/**
 * 参数定义
 */
var laydate = layui.laydate;

/**
 * 页面初始化
 */
$(function(){
	/*
	 * 时间控件渲染
	 */
	laydate.render({
		elem: '#timeConponent', 
		type: 'year',
		value: new Date(),
	});
})