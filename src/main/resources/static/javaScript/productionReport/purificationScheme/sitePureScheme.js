/**
 * 参数定义
 */
var laydate = layui.laydate,
	nowDateStr = new Date().toLocaleString(),
	currentTableHead = [],
	currentTableBody = [],
	currentTableHeadTitle = "",
	tableHeadUrl = ipPort + '/productionReport/purificationScheme/tableHead',
	tableDataUrl = ipPort + '/productionReport/purificationScheme/tableData',
	inspectionUrl = ipPort + '/productionReport/purificationScheme/inspectionNames',
	form = layui.form,
	table = layui.table,element = layui.element,
	paramJson = {
		time : nowDateStr.split( ' ' )[0].replace( /\//g , '-' ),
		currentScheme : ""
	},
	tableObj;

/**
 * 页面初始化
 */
$(function(){
	console.log( '-------页面初始化--------' );	
	form.render(); 
	
	//接受url传递参数
	var urlTime =  getUrlParamValueByName( 'time' );
	console.log( urlTime );
	if( urlTime ){
		paramJson.time = urlTime;
	}		
	
	/*
	 * 时间控件渲染
	 */
	laydate.render({
		elem: '#timeConponent', 
		type: 'date',
		value: paramJson.time,
	});
	
	//接受url传递方案参数
	var urlScheme =  getUrlParamValueByName( 'scheme' );
	console.log( decodeURIComponent(urlScheme) );
	if( urlScheme ){
		paramJson.currentScheme = decodeURIComponent(urlScheme);
		
	}	
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
	
	$( '#search_button_search' ).click();
		
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
 * 初始化方案
 */
function searchPlan( jsonData ){
	console.log( '------- 初始化方案--------' );
	if( jsonData.state == 0 ){
		if( jsonData.data != null){
			//排序
			var orderBase=["1列","2列","3列","4列","5列","6列","7列","辅助单元"]
			,orderedInspectName = [];
			$.each( orderBase, function( index0, item0 ){	
				$.each( jsonData.data, function( index1, item1 ){	
					if( contains( item1.SCHEME_NAME, item0 ) ){
						var tempInspect = {};
						tempInspect.SCHEME_NAME = item1.SCHEME_NAME;
						tempInspect.SCHEME_ID = item1.SCHEME_ID;
						orderedInspectName.push( tempInspect );
					}
				})
			})
			
			$.each( orderedInspectName, function( index, item ){	
				var flag = '';
				if( item.SCHEME_NAME == paramJson.currentScheme ){
					console.log( paramJson.currentScheme );
					flag = 'selected';
				}
				$( '#inspection' ).append( 
						'<option value="' + item.SCHEME_ID + '"' + flag + '>' + item.SCHEME_NAME + '</option>' 
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
	
	console.log(currentTableHead);
	/*
	 * 生成表格
	 */
	tableObj.reload({
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
		    		.find( '.layui-table-box > .layui-table-main  td' )
		    		.filter( 'td[data-key^="1-1"]' ).remove();
		    	$( '#search_pure_table' ).next( '.layui-form' )
	    		.find( '.layui-table-box > .layui-table-main td' )
	    		.filter( 'td[data-key^="1-2"]' ).remove();
		    	$( '#search_pure_table' ).next( '.layui-form' )
	    		.find( '.layui-table-box > .layui-table-main td' )
	    		.filter( 'td[data-key^="1-3"]' ).remove();
		    	$( '#search_pure_table' ).next( '.layui-form' )
	    		.find( '.layui-table-box > .layui-table-main td' )
	    		.filter( 'td[data-key^="1-4"]' ).remove();
//		    	$( '#search_pure_table' ).next( '.layui-form' )
//	    		.find( '.layui-table-box > .layui-table-main td' )
//	    		.filter( 'td[data-key^="1-5"]' ).remove();
//		    	$( '#search_pure_table' ).next( '.layui-form' )
//	    		.find( '.layui-table-box > .layui-table-main td' )
//	    		.filter( 'td[data-key^="1-6"]' ).remove();
//		    	
//		    	//去掉数据行的左右padding
//		    	$( '#search_pure_table' ).next( '.layui-form' )
//		    		.find( '.layui-table-box > .layui-table-header tr:nth-child(7) th div' )
//		    		.css( { "padding" : "0px" } );
//		    	
		    	//获取表格返回数据
		    	currentTableBody = res.data;
		    }
	});
	
	return false;
}

/**
 * 更新当前查询条件中参数
 */
function getSearchCondition(){	
	var chooseStr = $( '#inspection' ).next().find( 'dl .layui-this' ).attr( 'lay-value' );
	console.log( chooseStr );
	paramJson = {
		time: $( '#timeConponent' ).val(),
		currentScheme : chooseStr
	}
	
	currentTableHeadTitle = $( '#inspection' ).next().find( 'dl .layui-this' ).text();
}

/**
 * 导出报表按钮单击事件执行函数
 */
function exportExcelBCE(){
	console.log('导出报表按钮单击事件触发……');
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
    a.download = "净化方案巡检表.xls";
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
	    cols : currentTableHead ,
	    done : function( res , curr , count ){
	    	//去掉数据的多余列
	    	$( '#search_pure_table' ).next( '.layui-form' )
    		.find( '.layui-table-box > .layui-table-main  td' )
    		.filter( 'td[data-key^="1-1"]' ).remove();
    	$( '#search_pure_table' ).next( '.layui-form' )
		.find( '.layui-table-box > .layui-table-main td' )
		.filter( 'td[data-key^="1-2"]' ).remove();
    	$( '#search_pure_table' ).next( '.layui-form' )
		.find( '.layui-table-box > .layui-table-main td' )
		.filter( 'td[data-key^="1-3"]' ).remove();
    	$( '#search_pure_table' ).next( '.layui-form' )
		.find( '.layui-table-box > .layui-table-main td' )
		.filter( 'td[data-key^="1-4"]' ).remove();
	    	//获取表格返回数据
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
	var headLength = tableHeadData[5].length-1;
	var tableBefore = '<table  border="1" cellspacing="0" font-size="18px" text-align="center" >'
		,tableEnd = "</table>";	
	
	//生成标题
	var tableBody = '<tr style="height:50px;font-size:28px;font-weight:500;text-align:center">';
	var l = 0;
	for( var i = 0; i <=headLength; i = i+9 ){
		l = headLength - i + 1;
		if( l >= 9 ){
			tableBody = tableBody + 
			'<td colspan="9" style="text-align=center">'  
				+ currentTableHeadTitle 
			+ ' </td>';	
		}else{
			tableBody = tableBody + 
			'<td colspan="' + l + '" style="text-align=center">'  
				+ currentTableHeadTitle 
			+ ' </td>';
		}	
	}
	tableBody = tableBody + '</tr>';
	
	//生成表头	
	var pageColspanNum = 9  //标准excel页面的列数
	,leftNum = pageColspanNum //每次循环后剩余的未使用的列数
	,leftColspanNum = 0;  //每次循环后剩余的还需要继续合并的列数
	//生成一级表头	
	tableBody = tableBody + '<tr  style="height:30px;text-align:center">'	
	try{ 
		var i = 0;
		for( var j = 0; j < tableHeadData[0].length; j++ ){
			i++ ;
			var item1 = tableHeadData[0][j];		
			//计算colspan			
			if( j == 0 && i==1 ){
				leftColspanNum = item1.colspan?item1.colspan:1; 
			}				
			if( leftColspanNum < leftNum ){
				tableBody = tableBody
				+ '<td  style="width:110px" colspan=' + leftColspanNum + ' align="center">' 
				+ item1.title + '</td>';
				
				leftNum = leftNum - leftColspanNum;
				leftColspanNum = 0;
				var tempNum = tableHeadData[0][j+1].colspan;
				leftColspanNum = tempNum?tempNum:1; 
			}else if( leftColspanNum == leftNum  ){
				tableBody = tableBody
				+ '<td  style="width:110px" colspan=' + leftColspanNum + ' align="center">' 
				+ item1.title + '</td>';
				
				leftNum = leftNum - leftColspanNum;
				leftColspanNum = 0;
				leftNum = pageColspanNum;
				var tempNum = tableHeadData[0][j+1].colspan;			
				leftColspanNum = tempNum?tempNum:1; 				
			}else {
				tableBody = tableBody
				+ '<td  style="width:110px" colspan=' + leftNum + ' align="center">' 
				+ item1.title+ '</td>';
				
				leftColspanNum = leftColspanNum - leftNum;
				leftNum = pageColspanNum;	
				j--;
			}
		} 
    }catch(err){
       console.log(err);
    }
	tableBody = tableBody + "</tr>";
	console.log(tableBody);

	//生成二到六级表头
	var colspan;
	tableHeadData.splice(0,1);
	$.each( tableHeadData, function( index, item ){
		tableBody = tableBody + '<tr  style="height:30px;text-align:center">';		
		$.each( item, function( index1, item1 ){
			colspan = item1.colspan?item1.colspan:1;
			tableBody = tableBody
				+ '<td  style="width:110px" colspan=' + colspan + ' align=' + item1.align + '>' 
				+ item1.title+ '</td>';
		} )
		tableBody = tableBody + "</tr>";
	} )
	
	//生成表数据
	//console.log( '生成静态表格-生成表头……tableBody' + tableBody );
	var position  = "",length = tableHeadData.length-1;
	flagData = tableHeadData[length];
	$.each( tableBodyData, function( index, item ){
		tableBody = tableBody + '<tr  style="height:30px">';		
		for( var o in flagData ){
			position = flagData[o].field;
			if( item[position] ){
				tableBody = tableBody 
				+ '<td style="width:110px;text-align:center">' + item[position] + '</td>';
			}else{
				tableBody = tableBody 
				+ '<td style="width:110px;text-align:center" >' + '' + '</td>';
			}
			
			
		}
		tableBody = tableBody + "</tr>";
	})
	
	//console.log( '生成静态表格-生成数据……tableBody' + tableBody );	
	$( 'body' ).append( '<div id="excelTempDiv" style="display:none"></div>' );
	$( '#excelTempDiv' ).html('');
	$( '#excelTempDiv' ).append( tableBefore + tableBody + tableEnd );
}


