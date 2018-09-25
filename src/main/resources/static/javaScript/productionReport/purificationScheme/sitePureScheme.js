/**
 * 参数定义
 */
console.log(new Date().toLocaleString());
var laydate = layui.laydate,
	nowDateStr = new Date().toLocaleString(),
	currentTableHead = [],
	currentTableHeadTitle = "",
	tableHeadUrl = '/productionReport/purificationScheme/tableHead',
	tableDataUrl = '/productionReport/purificationScheme/tableData',
	form = layui.form,
	table = layui.table,
	paramJson = {
		time : nowDateStr.split( ' ' )[0].replace( /\//g , '-' ),
		currentScheme : ""
	};

/**
 * 页面初始化
 */
$(function(){
	form.render(); 
	
	/*
	 * 时间控件渲染
	 */
	laydate.render({
		elem: '#timeConponent', 
		type: 'date',
		value: paramJson.time,
	});
	
	/*
	 * 标题加时间
	 */
	addTimeNoteToTitle();
	
	/*
	 * 动态获取表头
	 */
	getSearchCondition();
	getTableHead( tableHeadUrl , paramJson );
		
	/*
	 * 表格配置参数生成表格
	 */
	console.log(5);
	generateTable();
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
		$title = $( '#search_waterEC_title' ).find( 'div' ),
		stableTitle = $title.text();
	$title.html( timeNote + stableTitle );
	$title.parent().addClass( 'search_waterEC_title' );
	$title.addClass( 'search_waterEC_title_div' );
}

/**
 * 查询按钮单击事件执行函数
 */
function searchButtonClickEvent(){
	console.log( '查询按钮单击事件触发……' );
	
	/*
	 * 更新时间方案参数
	 */
	getSearchCondition();
	
	/*
	 * 生成表头
	 */
	getTableHead( tableHeadUrl , paramJson );
	
	/*
	 * 生成表格
	 */
	generateTable();	
	
	return false;
}

/**
 * 更新当前查询条件中参数
 */
function getSearchCondition(){	
	var chooseStr = $( '#inspection' ).next().find( 'dl .layui-this' ).text();
	console.log( chooseStr);
	paramJson = {
		time: $( '#timeConponent' ).val(),
		currentScheme : chooseStr
	}
	
	currentTableHeadTitle = chooseStr;
}

/**
 * 导出报表按钮单击事件执行函数
 */
function exportExcelBCE(){
	console.log('导出报表按钮单击事件触发……');
}

/**
 * 请求表格表头
 */
function getTableHead( url , param ){
	console.log( '请求表格表头……' );
	ajaxByGet( url , param , getTableHeadSCB , false );
}

function getTableHeadSCB( jsonData ){
	console.log( '请求表格表头参数成功回调函数执行……' );
	if( jsonData.state == 0 ){
		if( jsonData.data != null){
			currentTableHead = jsonData.data;
		}else{
			layer.msg( '请求返回数据为空' , { icon : 2 });
		}
		
	}else{
		layer.msg( '请求连接发生错误' , { icon : 2 });
	}
}

/**
 * 表格配置参数生成表格
 */
function generateTable(){
	table.render({
	    elem : '#search_pure_table',
	    title : currentTableHeadTitle,
	    //width: '800px',
	    url : tableDataUrl,
	    page : false ,
	    where : paramJson,
	    toolbar: '<div style="width:100%;"> ' +
	    			'<div style="text-align:center;' +
	    						'font-size:24px;font-family:"华文仿宋"> ' +
	    				currentTableHeadTitle +
	    			'</div> ' +
	    		 '</div>',
	    request: {},
	    loading: true,
    	response: {
			statusName: 'state', 
			statusCode: 0 ,
			msgName: 'message', 
			countName: 'total', 
			dataName: 'data'
		},      	
	    cols : currentTableHead ,
	    done : function( res , curr , count ){
	    	//去掉数据的多余列
	    	$( '#search_pure_table' ).next( '.layui-form' )
	    		.find( '.layui-table-box > .layui-table-main td' )
	    		.filter( 'td[data-key^="1-5"]' ).remove();
	    	
	    	//去掉数据行的左右padding
	    	$( '#search_pure_table' ).next( '.layui-form' )
	    		.find( '.layui-table-box > .layui-table-header tr:nth-child(7) th div' )
	    		.css( { "padding" : "0px" } );
	    }
	 })
	 
	 table.render();
}
