/**
 * 参数定义
 */
var laydate = layui.laydate
	,form = layui.form
	,table = layui.table
	,inspectIdName={}
	,element = layui.element
	,nowDateStr = new Date().toLocaleString()
	,tableDataUrl = ipPort + '/productionReport/purificationScheme/status'
	,inspectionUrl = ipPort + '/productionReport/purificationScheme/inspectionNames'
	,staticTitlePart1 = "任务跟踪"
	,staticFlag = " - "
	,staticTitle =staticFlag +  staticTitlePart1
	,currentTableHeadTitle = ""
	,paramJson = {
		time : nowDateStr.split( ' ' )[0].replace( /\//g , '-' ),
		currentScheme : ""
	}
	,tableObj;

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
	 * 生成表格
	 */
	tableObj.reload({
		 elem : '#search_pure_table',
		    title : currentTableHeadTitle,
		    //width: '800px',
		    url : tableDataUrl,
		    page : false ,
		    where : paramJson,
//		    toolbar: '<div style="width:100%;"> ' +
//		    			'<div style="text-align:center;' +
//		    						'font-size:24px;font-family:"华文仿宋"> ' +
//		    				currentTableHeadTitle +staticTitle +
//		    			'</div> ' +
//		    		 '</div>',
		    request: {},
		    loading: true,
	    	response: {
				statusName: 'state', 
				statusCode: 0 ,
				msgName: 'message', 
				countName: 'total', 
				dataName: 'data'
			},      	
		    cols : [[
		    	{type:'numbers',width:'10%', title: '序号'}
			      ,{field:'id', width:'180', title: 'ID', hide: true}
			      ,{field:'', width:'20%', title: '方案名称',align:'center',templet: 
			         				'<span style="color: #c00;">'+ currentTableHeadTitle +'</span>'
			      				}
			      ,{field:'time', width:'10%', title: '任务时间',align:'center',templet: '#timeTpl'}
			      ,{field:'state', width:'15%', title: '执行人',align:'center'}
			      ,{field:'remark3', width:'20%', title: '完成时间',align:'center'}
			      ,{field:'state', width:'10%', title: '状态',align:'center',  templet: '#stateTpl'}
			      ,{fixed: 'right', width:'15%', title: '详情查看',align:'center', toolbar: '#barDemo'}
			    ]] 
	});
	
	return false;
}


//监听工具条
table.on('tool(search_pure_table)', function(obj){
	console.log( '-----数据表格查看详情单击事件-------' );
//	var data = obj.data; //获得当前行数据
	var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
//	var tr = obj.tr; //获得当前行 tr 的DOM对象
 
	if(layEvent === 'detail'){ //查看
		console.log( paramJson );
		
		//跳转
		location.href = ipPort + "/html/productionReport/purificationScheme/sitePureScheme.html?"
			+ "time=" + paramJson.time + "&scheme=" + encodeURIComponent(inspectIdName[paramJson.currentScheme]);		
	} else {
		
	}
});

/*
 * 事件绑定
 */	
//$( '.layui-btn' ).on( 'click', function(){
////	var $this=$(this), oType=$this.attr('operate');
////	clickE[oType] ? clickE[oType].call(this, $this ) : '';
//	var $this=$(this), oType=$this.attr('operate');
//	clickE[oType] ? clickE[oType].call(this) : '';
//	return false;
//})

$( function(){
	/*
	 * 查询按钮单击事件
	 */
	$( '#search_button_search' ).on( 'click' , searchButtonClickEvent);
})

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
	 * 动态生成方案名称
	 */
	ajaxByGet( inspectionUrl , {} , searchPlan , false );
	
	/*
	 * 标题加时间
	 */
	addTimeNoteToTitle();
	
	/*
	 * 加载表格
	 */
	generateTable();
	
	$( '#search_button_search').click();
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
			var orderBase=["1","2","3","4","5","6","7"]
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
				inspectIdName[item.SCHEME_ID] = item.SCHEME_NAME;
				$( '#inspection' ).append( 
						'<option value="' + item.SCHEME_ID + '">' + item.SCHEME_NAME + '</option>' 
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
 * 表格配置参数生成表格
 */
function generateTable(){
	console.log('----------表格配置参数生成表格-------');
	console.log(currentTableHeadTitle);
	tableObj = table.render({
	    elem : '#search_pure_table',
	    title : currentTableHeadTitle,
	    //width: '800px',
	    url : tableDataUrl,
	    page : false ,
	    where : paramJson,
//	    toolbar: '<div style="width:100%;"> ' +
//	    			'<div style="text-align:center;' +
//	    						'font-size:24px;font-family:"华文仿宋"> ' +
//	    				currentTableHeadTitle + 
//	    			'</div> ' +
//	    		 '</div>',
	    request: {},
	    loading: true,
    	response: {
			statusName: 'state', 
			statusCode: 0 ,
			msgName: 'message', 
			countName: 'total', 
			dataName: 'data'
		},      	
	    cols : [[
			  {type:'numbers',width:'10%', title: '序号'}
		      ,{field:'id', width:'180', title: 'ID', hide: true}
		      ,{field:'', width:'20%', title: '方案名称',align:'center',templet: 
		         				'<span style="color: #c00;">'+ currentTableHeadTitle +'</span>'
		      				}
		      ,{field:'time', width:'10%', title: '任务时间',align:'center',templet: '#timeTpl'}
		      ,{field:'state', width:'15%', title: '执行人',align:'center'}
		      ,{field:'remark3', width:'20%', title: '完成时间',align:'center',templet: '#compeletTimeTpl'}
		      ,{field:'state', width:'10%', title: '状态',align:'center',  templet: '#stateTpl'}
		      ,{fixed: 'right', width:'15%', title: '详情查看',align:'center', toolbar: '#barDemo'}
		    ]] ,	   
	    id : 'idTest'
	 })
	 table.render();

}



