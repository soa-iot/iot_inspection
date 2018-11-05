/**
 * 参数定义
 */


/**
 * 页面初始化
 */
$(function(){
	console.log( 'index页面初始化……' );
	/*
	 * 读取导航栏提示信息
	 */
	
	
	/*
	 * 导航栏显示用户名
	 */
	var userName = getCookie1( 'userName' ) ;
	$( '#showUserName a:first' ).html( 
		userName.substr( 1, userName.length - 2 ) +
		' <span class="caret"></span> '
	);
	
	/*
	 * 显示主页内容
	 */
	$( '#indexContent' )
		.load( 'mechatronicsProblemNode/problemIndex.html #body', function( returnDom ){
			$( returnDom ).find( 'script' ).appendTo( '#indexContent' );
		}); 
})


/**
 * 事件绑定
 */
$(function(){
	/*
	 * logo图片单击事件
	 * 流程主页单击事件
	 * 快捷导航内元素单击事件
	 * 
	 * 侧边导航栏单击事件
	 */
	
	/*
	 * 顶部导航栏“流程主页”单击事件
	 */
	$( '#processIndex' ).on( 'click', processIndexClickEvent );
	
	/*
	 * 侧边导航栏“问题上报”单击事件
	 */
	$( '#problemReportIndex' ).on( 'click', problemReportClickEvent );
	
	/*
	 * 退出按钮单击事件
	 */
	$( '#indexOut' ).on( 'click', indexOutClickEvent )
	
})

/**
 * 顶部导航栏流程主页单击事件
 */
function processIndexClickEvent(){
	console.log( '顶部导航栏流程主页单击事件……' );
	/*
	 * load主页
	 */
	$( '#indexContent' )
		.load( 'mechatronicsProblemNode/problemIndex.html #body', function( returnDom ){
			console.log( returnDom );
			$( returnDom ).find( 'script' ).appendTo( '#indexContent' );
		});
}

/**
 * 侧边导航栏“问题上报”单击事件回调函数
 */
function problemReportClickEvent(){
	console.log( '侧边导航栏问题上报单击事件……' );
	/*
	 * 跳转页面
	 */
	$( '#indexContent' )
		.load( 'mechatronicsProblemNode/problemReport.html #body', function( returnDom ){
			console.log( returnDom );
			$( returnDom ).find( 'script' ).appendTo( '#indexContent' );
		});
}

/**
 * 退出按钮-单击事件回调函数
 */
function indexOutClickEvent(){
	console.log( '退出按钮-单击事件回调函数……' );
	/*
	 * 删除cookie
	 */
	//delCookie( "userName" );
	//delCookie( "userOrganization" );
	//delCookie( "userRole" );
	clearAllCookie2();
	
	/*
	 * 跳转登录页面
	 */
	window.location.href = "/html/process/login.html";

}

