/**
 * 参数定义
 */
var laydate = layui.laydate,
	paramJson = {
		startTime: getFullLastMonthDate('-'),
		endTime : getFullNowDate('-')
	};

/**
 * 页面初始化
 */
$(function(){
	/*
	 * 时间控件渲染
	 */
	laydate.render({
		elem: '#timeConponent', 
		//type: 'date',
		range: true,
		value: getFullLastMonthDate('-') + ' - ' + getFullNowDate('-') ,
	});
	
})

/**
 * 事件绑定
 */
$(function(){
	/*
	 * 查询按钮单击事件
	 */
	$( '#search_button_search' ).on( 'click' , searchButtonClickEvent );
	
	/*
	 * 导出报表按钮单击事件
	 */
	$( '#search_button_export' ).on( 'click' , exportExcelBCE );
})

/**
 * 查询按钮单击事件执行函数
 */
function searchButtonClickEvent(){
	console.log( '查询按钮单击事件触发……' );
	
	/*
	 * 获取选择的时间段，更新参数
	 */
	var datesArr =  $( '#timeConponent' ).val().split( /\s+-\s+/ ) ;
	console.log( datesArr );
	paramJson.startTime = datesArr[0];
	paramJson.endTime = datesArr[1];
	
	/*
	 * 清空组件容器，更新
	 */	
	//清空
	var tableJsArr = [];
	$.each( $('.component_container' ), function( index, item ){
		$( item ).find( ':not(script)' ).remove();
		tableJsArr.push( $( item ).html() );
		$( item ).html('');
	})
	//更新
	$.each( $('.component_container'), function( index, item ){
		$( item ).html( tableJsArr[index] ); 
	})
	
}

/**
 * 导出报表按钮单击事件执行函数
 */
function exportExcelBCE(){
	console.log('导出报表按钮单击事件触发……');
}