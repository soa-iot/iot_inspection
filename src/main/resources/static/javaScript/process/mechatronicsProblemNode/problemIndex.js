/**
 * 参数定义
 */
var siouInfoUrl = "",
	nowDate = new Date(),
	tempDate = ["日", "一", "二", "三", "四", "五", "六"],
	nowWeekNum = tempDate[ nowDate.getDay() ];
/**
 * 立即执行函数
 */
(function(){
	/*
	 * 定时刷新时间
	 */
	var dateTimer = setInterval( function(){
		nowDate = new Date(),
		loadRefreshTime();
	}, 10000 );
})()

/**
 * 页面初始化
 */
$(function(){
	console.log( '流程主页初始化……' );
	/*
	 * 导航栏显示用户名
	 */
	var userNameTemp = getCookie1( 'userName' ) ,
		userName = userNameTemp.substr( 1, userNameTemp.length - 2 ),
		userOrganizationTemp = getCookie1( 'userOrganization' ) ,
		userOrganization = userOrganizationTemp.substr( 1, userOrganizationTemp.length - 2 ),
		userRoleTemp = getCookie1( 'userRole' ) ,
		userRole = userRoleTemp.substr( 1, userRoleTemp.length - 2 );
	$( '#userNameProblemIndex' ).html( 
		' <span style="font-size:28px"> ' +
		'	<strong>' + userName + '</strong> ' + 
		' 	<span style="font-size:18px">&nbsp&nbsp' + userOrganization + '  </span>' + 
		' 	<span style="font-size:14px">&nbsp&nbsp' + userRole + ' </span>' + 
		' </span> ' + 
    	' <p> ' + 
		'	 欢迎登录流程管理系统！ ' + 
		'<a href="' + siouInfoUrl + '" target="_blank">' + 
		'	重庆斯欧信息    ' + 
		'</a>  ' + 
    	'</p>  ' 
	);
	
	/*
	 * 显示上报页面日期\时间内信息
	 */
	loadRefreshDate();
	loadRefreshTime();	
	
	/*
	 * 
	 */
})


/**
 * 事件绑定
 */
$(function(){
	/*
	 * logo图片单击事件
	 * 流程主页单击事件
	 * 快捷导航内元素单击事件
	 * 退出按钮单击事件
	 * 侧边导航栏单击事件
	 */
	
})

/**
 * 加载、更新日期
 */
function loadRefreshDate(){
	$( '#nowWeekProblemIndex' ).html( 
		' <p>星期' + nowWeekNum + '</p> ' + 
		' <p> ' +
		' <span>' + nowDate.getFullYear() + '</span> ' + 
		'      年' + ( nowDate.getMonth() + 1 ) + '月' + nowDate.getDate() + '日' 
	);
}

/**
 * 加载、更新时间
 */
function loadRefreshTime(){
	$( '#nowTimeProblemIndex' ).html( 
		' <strong> ' + nowDate.getHours() + ":" + nowDate.getMinutes()  + ' </strong> '
	);
}

