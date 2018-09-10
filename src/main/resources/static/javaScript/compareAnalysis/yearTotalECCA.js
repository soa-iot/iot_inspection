/**
 * 参数定义
 */
var laydate = layui.laydate,
	paramJson = {
		time: new Date().getFullYear()
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
		type: 'year',
		range: true,
		value: ( new Date().getFullYear()-1 ) + ' - ' + new Date().getFullYear() ,
	});
	
	/*
	 * 获取选择的所有年份
	 */
	var yearsArr = getRangeYearArr( $('#timeConponent').val() );
	
	/*
	 * 动态生成表格
	 */
	activeGenerateTable( yearsArr );
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
	 * 获取选择的所有年份
	 */
	var yearsArr = getRangeYearArr( $('#timeConponent').val() );
	
	/*
	 * 动态生成表格
	 */
	activeGenerateTable( yearsArr );
	
	/*
	 * 清空组件容器，更新
	 
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
	*/
}

/**
 * 获取选择的所有年份
 */
function getRangeYearArr( originalStr ){
	var arr = originalStr.split( '-' ),
		arrs = [ $.trim( arr[0] ) ],
		flag = parseInt( $.trim( arr[0] ));
	while( flag < parseInt( $.trim( arr[1] )) ){
		flag = flag + 1;
		arrs.push( flag + '' );
	}
	return arrs;
}

/**
 * 动态生成表格
 */
function activeGenerateTable( arrParam ){
	console.log( '动态生成表格……' );	
	$( arrParam ).each( function( index, item ){
		$( '.layui-fluid' ).append(
			' <div class="layui-row layui-col-space15"> ' + 
			' 	<div class="layui-col-md12" > ' +
			' 		<div class="component_container" > ' +
			'       	<script type="text/javascript" >  ' +
			'               	var param = ' + arrParam[index] + ' ; ' + 
			'           	( function(){  '	+	
								$( '#template' ).html() +
								'       	;console.log($currentObj);  ' +
			'           	}( param ))   '	+	
			'           </script> ' +
			' 		</div> ' +
			' 	</div> ' +
			' </div> '
		);
	} )
}

/**
 * 导出报表按钮单击事件执行函数
 */
function exportExcelBCE(){
	console.log('导出报表按钮单击事件触发……');
}