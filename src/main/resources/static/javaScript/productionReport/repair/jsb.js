/**
 * 参数定义
 */
var laydate = layui.laydate,
	nowDateStr = new Date().getFullYear() + "-" + (new Date().getMonth() + 1),
	chooseDayStr , 
	currentTableHead = [],
	currentTableBody = [],
	currentTableHeadTitle = "",
	tableHeadUrl = ipPort + '/repair/headconfig',
	tableDataUrl = ipPort + '/repair/value',
	inspectionUrl = ipPort + '/repair/plan',
	daysUrl = ipPort + '/repair/day',
	form = layui.form,
	table = layui.table,element = layui.element,
	paramJson = {
		time : nowDateStr,
		currentScheme : "",
		timeStr: ''
	},
	tableObj;

/**
 * 页面初始化
 */
$(function(){
	form.render(); 
	
	/*
	 * 时间控件渲染
	 */
	console.log( paramJson.time );
	laydate.render({
		elem: '#timeConponent', 
		type: 'month'
		,format: 'yyyy-MM'
		,value: paramJson.time
		,done: function(value, date, endDate){
//		    console.log(value); //得到日期生成的值，如：2017-08-18
//		    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
//		    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		    ajaxByGet( daysUrl , {"time": value} , getDaysSF , false );
		}
	});
	
	/*
	 * 日期动态生成
	 */
	var arr = paramJson.time.split("-"),dateParam;
	if( arr[1] < 10 ){
		dateParam = arr[0] + "-0" + arr[1];
	}else{
		dateParam = arr[0] + "-" + arr[1];
	}
	ajaxByGet( daysUrl , {"time": dateParam} , getDaysSF , false );
	
	/*
	 * 动态生成方案名称
	 */
	ajaxByGet( inspectionUrl , {} , searchPlan , false );
	
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
	/*
	 * 日期控件值改变事件
	 */
	
})

/**
 * 获取日期回调函数
 */
function getDaysSF( jsonData ){
	console.log( '-------获取日期回调函数---------' );
	$( '#day' ).empty();
	if( jsonData ){
		if( jsonData.state == 0 && jsonData.data != null){
			$.each( jsonData.data, function( index, item ){
				$( '#day' ).append( 
						'<option value="' + $.trim( item.TIME ) + '">' 
						+ $.trim( item.TIME )
						+ '</option>' 
				); 
			})
			form.render();
		}else{
			layer.msg( '返回数据为空' , { icon : 2 });
		}		
	}else{
		layer.msg( '连接发生错误' , { icon : 2 });
	}
}

/**
 * 标题加时间
 */
function addTimeNoteToTitle(){
	var timeNote = $( '#timeConponent' ).val() + '月',
		$title = $( '#search_waterEC_title' ).find( 'div' ),
		stableTitle = $title.text();
	$title.html( timeNote + stableTitle );
	$title.parent().addClass( 'search_waterEC_title' );
	$title.addClass( 'search_waterEC_title_div' );
}
/**
 * 更新时间格式
 */
function formatDate(){
	var y = new Date().getFullYear();
	var m = new Date().getMonth() + 1;
	if( m < 10 ){
		m = '0' + m;
	}
	param.time = y + "-" + m;
}

/**
 * 初始化方案
 */
function searchPlan( jsonData ){
	console.log( '------- 初始化方案--------' );
	if( jsonData.state == 0 ){
		if( jsonData.data != null){
//			$.each( jsonData.data, function( index, item ){
//				$( '#inspection' ).append( 
//						'<option value="' + item.SCHEME_ID + '">' + item.SCHEME_NAME + '</option>' 
//				); 
//			})		
			$.each( jsonData.data, function( index, item ){
				var s = '';
				if( $.trim( item.PLANID ) == '2' ){
					s = "遂宁龙王庙净化厂工艺管道巡检记录表";
				}else{
					s = "遂宁龙王庙净化厂静设备巡检记录表";
				}
				$( '#inspection' ).append( 
						'<option value="' + $.trim( item.PLANID ) + '">' 
						+ s
						+ '</option>' 
				); 
			})
			element.init();
			form.render();
		}else{
			layer.msg( '请求方案返回数据为空' , { icon : 2 });
		}		
	}else{
		layer.msg( '请求方案连接发生错误' , { icon : 2 });
	}
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
	var chooseStr = $( '#inspection' ).next().find( 'dl .layui-this' ).attr( 'lay-value' ),
	timeStr = $( '#day' ).next().find( 'dl .layui-this' ).attr( 'lay-value' );
	console.log( chooseStr );
	console.log( timeStr );
	paramJson = {
		timeStr: timeStr,
		currentScheme : chooseStr,
		time: $( '#timeConponent' ).val() 
	}
	
	currentTableHeadTitle = $( '#inspection' ).next().find( 'dl .layui-this' ).text();
}

/**
 * 导出报表按钮单击事件执行函数
 */
function exportExcelBCE(){
	console.log('导出报表按钮单击事件触发……');
	console.log( currentTableHead );
	console.log( currentTableBody );
	generStaticTable( currentTableHead, currentTableBody );	
	// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，
	// 设置charset为urf-8以防止中文乱码
    var html = "<html><head><meta charset='utf-8' /></head><body>" 
    	+ $( "#excelTempDiv" ).html() + "</body></html>";
    // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
    var blob = new Blob( [html], { type: "application/vnd.ms-excel" });
    $( 'body' ).append('<a id="aExport" style="display:none"></a>');
    var a = $( '#aExport' )[0];
    // 利用URL.createObjectURL()方法为a元素生成blob URL
    a.href = URL.createObjectURL(blob);
    // 设置文件名
    a.download = "维修设备巡检表.xls";
    document.getElementById("aExport").click();
    //$( '#aExport' ).click();
	return false;
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
	console.log( '----------表格配置参数生成表格-----------' );
	console.log( currentTableHead );
	tableObj = table.render({
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
	    cols :[currentTableHead] ,
	    done : function( res , curr , count ){	     	
	    	//去掉数据行的左右padding
//	    	$( '#search_pure_table' ).next( '.layui-form' )
//	    		.find( '.layui-table-box > .layui-table-header tr:nth-child(7) th div' )
//	    		.css( { "padding" : "0px" } );
//	    	
//	    	//获取表格返回数据
	    	currentTableBody = res.data;
	    },
	    id : 'idTest'
	 })
	 table.render();

}


/**
 * 生成静态表格
 */
function generStaticTable( tableHeadData, tableBodyData ){
	console.log( '生成静态表格……' );
	var tableBefore = "<table>",tableEnd = "</table>";	
	var tableBody = "";
	var colspan ;
	tableBody = tableBody + "<tr>";	
	$.each( tableHeadData, function( index, item ){			
		colspan = item.colspan?item.colspan:1;
		tableBody = tableBody
			+ '<td colspan=' + colspan + ' align=' + item.align + '>' 
			+ item.title+ '</td>';		
	} )
	tableBody = tableBody + "</tr>";	
	//console.log( '生成静态表格-生成表头……tableBody' + tableBody );

	var position  = "",flagData = tableHeadData,num=1;
	$.each( tableBodyData, function( index, item ){
		tableBody = tableBody + "<tr>";
		for( var o in flagData ){			
			position = flagData[o].field;
			if( item[position] ){
				tableBody = tableBody 
				+ '<td align="center">' + item[position] + '</td>';
			}else{				
				if( flagData[o].type == 'numbers' ){
					tableBody = tableBody 
					+ '<td align="center">' + num + '</td>';
					num ++;
				}else{
					tableBody = tableBody 
					+ '<td align="center">' + '' + '</td>';
				}				
			}			
		}
		/*
		$.each( flagData, function( index1, item1 ){
			console.log( '生成静态表格……item.field' + item1.field );
			position = item1[field];
			tableBody = tableBody 
				+ '<td align="center">' + item[position] + '</td>';
		} )
		*/
		tableBody = tableBody + "</tr>";
	})
	
	//console.log( '生成静态表格-生成数据……tableBody' + tableBody );	
	$( 'body' ).append( '<div id="excelTempDiv" style="display:none"></div>' );
	$( '#excelTempDiv' ).html('');
	$( '#excelTempDiv' ).append( tableBefore + tableBody + tableEnd );
}


