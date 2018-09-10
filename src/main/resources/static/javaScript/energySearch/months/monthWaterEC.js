/**
 * 参数定义
 */
var laydate = layui.laydate,
	nowDateStr = new Date().toLocaleString(),
	paramJson = {
	time: nowDateStr.substr( 0 , nowDateStr.lastIndexOf( '/' ) ).replace( '/' , '-' )
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
		type: 'month',
		value: paramJson.time,
	});
	
	/*
	 * 标题加时间
	 */
	addTimeNoteToTitle();
})

/**
 * 事件绑定
 */
$( function(){
	/*
	 * 查询按钮单击事件
	 */
	$( '#search_button_search' ).on( 'click' , searchButtonClickEvent);
	/*
	 * 导出报表按钮单击事件
	 */
	$( '#search_button_export' ).on( 'click' , exportExcelBCE);
})

/**
 * 标题加时间
 */
function addTimeNoteToTitle(){
	var timeNote = $( '#timeConponent' ).val().replace( '-' , '年') + '月',
		stableTitle = $( '#search_rangeWater_title' ).find( 'div' ).text();
	console.log( timeNote );
	$( '#search_rangeWater_title' ).find( 'div' ).text( timeNote + stableTitle );
}

/**
 * 查询按钮单击事件执行函数
 */
function searchButtonClickEvent(){
	console.log( '查询按钮单击事件触发……' );
	
	/*
	 * 更新时间参数
	 */
	console.log($('#timeConponent').val());
	paramJson = {
			time: $('#timeConponent').val()
	}
	
	/*
	 * 清空组件容器，更新
	 */
	//清空
	var tableJsArr = [];
	$.each( $('.component_container'), function( index, item ){
		$( item ).find(':not(script)').remove();
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