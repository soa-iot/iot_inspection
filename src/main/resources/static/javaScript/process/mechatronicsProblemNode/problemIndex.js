/**
 * 参数定义
 */
var siouInfoUrl = "",
	layer = layui.layer,
	nowDate = new Date(),
	tempDate = ["日", "一", "二", "三", "四", "五", "六"],
	nowWeekNum = tempDate[ nowDate.getDay() ],
	//代办任务url
	baseUnfinishedTaskUrl = "/processBusiness/runtimeTask",
	//历史人物url
	baseHistoryTaskUrl = "/processBusiness/historyTask",
	//查询代办处理连接页面url
	searchBaseDealProblemUrl = "/processBusiness/problemDealUrl",
	//代办处理连接页面url
	baseDealProblemUrl = ipPort + "/html/process/mechatronicsProblemNode",
	//当前piid
	currentDealPiid = "",
	//当前处理流程tsid
	currentDealTsid = "";
		
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
	 * 查询代办任务
	 */
	searchUnfinishedTask();
	
	/*
	 * 查询用户历史任务
	 */
	searchHistoryTask();
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

/**
 * 查询代办任务
 */
function searchUnfinishedTask(){
	console.log( '查询代办任务-主页……' );
	var url = ipPort + baseUnfinishedTaskUrl + "/" + currentUser;
	ajaxByGet( url, {}, searchUnfinishTaskSuccessFunction );
}

/**
 * 查询代办任务回调函数
 */
function searchUnfinishTaskSuccessFunction( jsonData ){
	console.log( '查询代办任务-成功回调函数……' );
	var unfinishedProblemNumJson = {
			"机电仪问题" : 0,
			"净化问题" : 0,
			"其他问题" : 0,
			"事故隐患问题" : 0
	};	
	if( jsonData != null ){
		if( jsonData.state == 0 ){
			var problemInspections = jsonData.data;
			
			//显示代办任务数量和类型
			$( '#mechanicProblemCalcul' ).find( 'button span' )
				.text( problemInspections.length );
			
			//获取、设置代办数量
			$.each( problemInspections, function( index, item ){
				unfinishedProblemNumJson[item.problemType] ++;				
			})
			console.log( unfinishedProblemNumJson );
			$.each( $( '#problemIndexTotalUnfinished span' ), function( index, item ){				
				var text = $.trim( $( item ).parent().find( 'p' ).text() );
				$( item ).text( unfinishedProblemNumJson[text] );
			})
			
			//显示代办任务具体信息
			$.each( problemInspections, function( index, item ){
				$( '#userUnfinishedTasdList' ).after(
						' <div class="panel-body"> ' +
						'	<div class="w15 pull-left">  ' +
						'   	<img src="/picture/noavatar_middle.gif" width="25"  ' +
             			'			height="20" alt="图片" class="img-circle"> ' +
             					item.problemType +
						'	</div> ' +
						'   <div class="w15 pull-left">' + 
								(item.currentProgress || '&nbsp&nbsp')  + 
						'   </div> ' +
						'   <div class="w30 pull-left">' + 
								item.problemDescribe + 
						'   </div> ' +
						'   <div class="w25 pull-left text-center"> ' + 
								item.reportTime + 
						'   </div> ' +
						'   <div remark="remark" name="piid" style="display:none"> ' +
								item.piid +
						'   </div> ' +
						'   <div remark="remark" name="currentTsid" style="display:none"> ' +
								item.currentTsid + 
						'   </div> ' +
						'   <div remark="remark" name="currentPrid" style="display:none"> ' +
								item.currentPrid + 
						'   </div> ' +
						'   <div remark="remark" name="currentProgress" style="display:none"> ' +
								item.currentProgress + 
						'   </div> ' +
						'   <div class="w10 pull-left text-center"> ' +
						'   	<span class="text-green-main" onclick="dealUnfinisdedTask(this)">' +
						'			处理</span> ' +
						'   </div>   ' +
						' </div>   '					
				);      	
			} )
		}else{
			layer.msg( '查询代办任务失败', {icon : 2} )
		}
	}else{
		layer.msg( '请求失败', {icon : 2} )
	}
}

/**
 * 处理代办任务-单击事件
 */
function dealUnfinisdedTask( obj ){
	console.log( '处理代办任务-单击事件……' );
	/*
	 * 获取问题业务id，请求流程节点名称
	 */
	//设置piid
	currentDealPiid =$.trim( $( obj ).parent().siblings( 'div[name=currentPiid]' ).text() );
	currentDealTsid = $.trim( $( obj ).parent().siblings( 'div[name=currentPiid]' ).text() );	
	var currentPrid =$.trim( $( obj ).parent().siblings( 'div[name=currentTsid]' ).text() );
	console.log( '处理代办任务-单击事件……currentPiid:' + currentDealPiid );
	console.log( '处理代办任务-单击事件……currentDealTsid:' + currentDealTsid );
	console.log( '处理代办任务-单击事件……currentPiid:' + currentPiid );
	console.log( currentPrid );
	var searchDealUrl = searchBaseDealProblemUrl + "/" + currentPrid;
	ajaxByGet( searchDealUrl, {}, searchDealUrlSuccessFunction );	
}

/**
 * 用户历史任务-查询
 */
function searchHistoryTask(){
	console.log( '用户历史任务-查询……' );
	//获取查询参数
	var url = baseHistoryTaskUrl + "/" + currentUser ;
	var	problemTypeStr = "";
	$.each( $( '#problemInfoTotal' ).find( 'p' ), function( index, item ){
		problemTypeStr = problemTypeStr + $( item ).text() + ",";
	})
	problemTypeStr = problemTypeStr.substr( 0, problemTypeStr.length - 1 );
	console.log( '用户历史任务-查询……problemTypeStr:' + problemTypeStr );
	//请求数据
	ajaxByGet( url, { "problemTypeStr" : problemTypeStr }, searchHistoryTaskSuccessFunction );	
}

/**
 * 用户历史任务-查询成功回调函数
 */
function searchHistoryTaskSuccessFunction( jsonData ){
	console.log( '用户历史任务-查询成功回调函数……' );
	var totalProblemNumJson = {
			"机电仪问题" : 0,
			"净化问题" : 0,
			"其他问题" : 0,
			"事故隐患问题" : 0
	};	
	if( jsonData != null ){
		if( jsonData.state == 0 ){
			var problemInspections = jsonData.data;
			//遍历所有问题,计算问题类型数量
			console.log( problemInspections );
			$.map( problemInspections, function( item, index ){
				totalProblemNumJson[item.problemType] ++ ;
			})
			console.log( totalProblemNumJson );
			
			//赋值
			$.map( $( '#problemIndexTotalUnfinished h3' ), function( item, index ){
				var text = $.trim( $( item ).siblings( 'p' ).text() );
				$( item ).text( totalProblemNumJson[text] );
			})
		}else{
			layer.msg( '查询代办任务失败', {icon : 2} )
		}
	}else{
		layer.msg( '请求失败', {icon : 2} )
	}
}

/**
 * 查询Deal路径--成功回调函数
 */
function searchDealUrlSuccessFunction( jsonData ){
	console.log( '查询Deal路径--成功回调函数……' );
	if( jsonData != null ){
		if( jsonData.state == 0 ){
			var data = jsonData.data,
				urlName = data.split( ',' )[0];
			console.log( '查询Deal路径--成功回调函数……data:' + data );
			
			//跳转页面
			var wholeUrl = 
				baseDealProblemUrl + "/" + urlName + ".html?currentPiid=" + currentPiid ;
			var dataJson = {
				"currentDealPiid" : currentDealPiid	
			}
			$( '#indexContent' )
				.load( wholeUrl + ' #body', dataJson, function( returnDom ){
				$( returnDom ).find( 'script' ).appendTo( '#indexContent' );
			}); 
				
		}else{
			layer.msg( '查询代办任务失败', {icon : 2} )
		}
	}else{
		layer.msg( '请求失败', {icon : 2} )
	}
}

