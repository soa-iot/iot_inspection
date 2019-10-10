$(function(){
	/**
	 * 参数初始化
	 */
	var element = layui.element
	,form = layui.form
	,laydate = layui.laydate
	,table = layui.table
	,nowDate = new Date()
	,planName = "1"//分析化验方案的方案id（配置信息）
	,currPointName
	,currProjectName
	,stime
	,etime
	,currentTableBodys={}
	,currentTableHead={}
	,tableNum //table的个数标记
	,pointTypeUrl = '../../../analysis/points'
	,projectTypeUrl = '../../../analysis/projects'
	,headConfigUtl = '../../../analysis/headConfig1'
	,valueUrl = '../../../analysis'
	,init = {
			getTableHead: function(){			
				//动态生成表头
				ajaxByGet( headConfigUtl, {
					"planName": planName
					,"pointName": currPointName
					,"projectName": currProjectName }, sf.showTable ,false);			
			}			
	}
	,sf = {
			getPointType: function( jsonData ){
				console.log( '------初始化巡检单元--------' );
				console.log( jsonData );
				if( jsonData ){
					var data = jsonData.data;
					if( data && jsonData.state ==0 ){
						//动态生成巡检单元
						$.each( data, function( index, item ){
							$( '#points' ).append( 
									'<input type="radio" name="point" value="' + item + '" title="' + item + 
									'" lay-filter="point">' 
									);
						})		
						form.render();
					}else{
						layer.msg( jsonData.message, {icon:2} );
					}
				}else{
					layer.msg( '请求失败', {icon:2} );
				}
			},
			getProjectType: function( jsonData ){
				console.log( '------初始化巡检项目--------' );
				console.log( jsonData );
				$( '#projects').empty();
				if( jsonData ){
					var data = jsonData.data;
					if( data && jsonData.state ==0 ){
						//动态生成巡检项目
						$.each( data, function( index, item ){							
							$( '#projects').append( 
									'<input type="checkbox" name="project" value="' 
									+ item + '" title="' + item + 
									'" lay-filter="project">' 
									);
						})	
						form.render();
					}else{
						layer.msg( jsonData.message, {icon:2} );
					}
				}else{
					layer.msg( '请求失败', {icon:2} );
				}
			}
			,showTable: function( jsonData ){
				console.log( '------初始化表格--------' );
				console.log( jsonData );
				//动态生成表格区
				tableNum++;
				
				var tableId= 'table' + tableNum
				console.log(tableId);
				currentTableHead[tableId]=jsonData.data;
				$( '#tableArea' ).append(
						'  <div style="display:inline-block;vertical-align: top"> ' + 
						'  		<table class="layui-hide" id="table' + tableNum + '" ></table> ' + 
						'  </div>'  
				);
				if( jsonData ){
					var data = jsonData.data;
					if( data && jsonData.state ==0 ){						
						//生成表格
						table.render({
						    elem : '#table' + tableNum,
						    title : "分析化验方案表",
						    //width: '800px',
						    url : valueUrl,
						    page : false ,
						    method: 'get',
						    where : {
						    	"uname":currPointName						    	
						    	,"pname":currProjectName
						    	,"stime": $( '#stime' ).val()
						    	,"etime": $( '#etime' ).val()
						    },
						    loading: true,
					    	response: {
								statusName: 'state', 
								statusCode: 0 ,
								msgName: 'message', 
								countName: 'total', 
								dataName: 'data'
							},      	
						    cols :data ,
						    done : function( res , curr , count ){	
						    	for( var l=1; l<=tableNum; l++){
									 $( '#table' + l ).next('div').find('.layui-table-main').find('td[class=layui-table-col-special]').remove();
									 
						    	}
						    	//去掉数据行的左右padding
//						    	$( '#search_pure_table' ).next( '.layui-form' )
//						    		.find( '.layui-table-box > .layui-table-header tr:nth-child(7) th div' )
//						    		.css( { "padding" : "0px" } );
//						    	
//						    	//获取表格返回数据
						    	currentTableBody = res.data;
						    	console.log(tableId)
						    	console.log(currentTableBody)
						    	currentTableBodys[tableId]=currentTableBody;
						    },
						    id : 'idTest' + tableNum,
						 })					 
						 table.render(null,'#table' + tableNum);	
					}else{
						layer.msg( jsonData.message, {icon:2} );
					}
				}else{
					layer.msg( '请求失败', {icon:2} );
				}			
			}
	}
	,cf = {
			showProjectType: function( pointName ){
				console.log( '------巡检项目单击事件--------' );
				ajaxByGet( projectTypeUrl, {"name": "1","pointName": pointName}, sf.getProjectType );
			}
			,search: function(){
				console.log( '------查询按钮单击事件--------' );
				tableNum = 0;
				$('#tableArea').empty();
				currentTableHead.length=0;
				//动态获取数据展示
				var $checkedPro = $( '#projects' ).find( 'div[class*="layui-form-checked"]');
				$.each( $checkedPro, function( index, item ){
					//表头
					currProjectName = $.trim( $( item ).text() );
					console.log( currProjectName );
					init.getTableHead();
				})		
				
				return false;
			}
	}
	
	
	/**
	 * 页面初始化
	 */
	ajaxByGet( pointTypeUrl, {"name": planName}, sf.getPointType );
	
	//单选按钮监听
	form.on( 'radio(point)', function( data ){
//		console.log(data.elem); //得到radio原始DOM对象
//		console.log(data.value); //被点击的radio的value值
		currPointName = data.value;
		cf.showProjectType( data.value );		
	});  
	form.on( 'checkbox(project)', function( data ){
//		console.log(data.elem); //得到checkbox原始DOM对象
//		console.log(data.elem.checked); //是否被选中，true或者false
//		console.log(data.value); //复选框value值，也可以通过data.elem.value得到
//		console.log(data.othis); //得到美化后的DOM对象
//		init.getProjects();
	});
	
	laydate.render({
    	elem: '#stime',
    	type: 'date',
    	value: nowDate.getFullYear() + "-" + nowDate.getMonth() + "-" + (nowDate.getDay() -1 )
	})
	laydate.render({
    	elem: '#etime',
    	type: 'date',
    	value: nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1 )+ "-" + nowDate.getDay() 
	})
	
	$( '#search' ).on( 'click', cf.search );	
	
	
	/**
	 * 生成静态表格
	 */
	function generStaticTable( tableHeadData, tableBodyData ){
		console.log( '生成静态表格……' );
		console.log(tableHeadData);
		console.log( '生成静态表格数据……' );
		console.log(tableBodyData);
		var tableBefore = '<table  border="1" cellspacing="0" font-size="18px" text-align="center" >'
			,tableEnd = "</table>";	
		
		//生成标题
		var tableBody = '<tr style="height:40px;font-size:24px;font-weight:bold;text-align:center">';
		//var l = 0;
		for( var i = 0; i < 6; i++ ){
			
			if(i != 0){
				tableBody += '<tr style="min-height:30px;font-size:20px;font-weight:bold;text-align:center">';
			}
			for(var  key in currentTableHead){
				//console.log(tableHeadData[j][i].length);
				if(key != 'length'){
					for(var k = 0; k < tableHeadData[key][i].length; k++){
						if(k == 0){
							tableBody = tableBody + 
							'<td colspan="'+tableHeadData[key][i][k].colspan+'" style="text-align=center;min-width:150px;">'  
								+ tableHeadData[key][i][k].title 
							+ ' </td>';
						}else{
							tableBody = tableBody + 
							'<td colspan="'+tableHeadData[key][i][k].colspan+'" style="text-align=center;">'  
								+ tableHeadData[key][i][k].title 
							+ ' </td>';	
						}
					}
				}
				
			}
			tableBody = tableBody + '</tr>';

			}
		
		//生成表数据
		//console.log( '生成静态表格-生成表头……tableBody' + tableBody );
		var tableBodyLength  = 0;
		
		for(var  key in currentTableBodys){
			var tableBodysLength = currentTableBodys[key].length;
			tableBodyLength = tableBodyLength < tableBodysLength?tableBodysLength : tableBodyLength;
		}
		console.log(tableBodyLength);
		
		for(var  i = 0; i < tableBodyLength; i++){
			tableBody = tableBody + '<tr  style="height:30px">';
			for(var  key in currentTableBodys){
				var tableBodyTr = currentTableBodys[key][i];
				console.log("key:"+key);
				if(tableBodyTr != null){
					for(var  key in tableBodyTr){
						if(key != 'LAY_TABLE_INDEX'){
							tableBody = tableBody + '<td style="text-align:center">' + tableBodyTr[key] + '</td>';
						}
						//console.log(tableBody);
					}
				}else{
					
					console.log('空格长度：'+tableHeadData[key][5].length);
					for(var  j = 0; j < tableHeadData[key][5].length; j++){
						tableBody = tableBody + '<td style="text-align:center"></td>';
						//console.log(tableBody);
					}
				}
				
			}
			tableBody = tableBody + "</tr>";
		}
		
		//console.log( '生成静态表格-生成数据……tableBody' + tableBody );	
		$( 'body' ).append( '<div id="excelTempDiv" style="display:none"></div>' );
		$( '#excelTempDiv' ).html('');
		$( '#excelTempDiv' ).append( tableBefore + tableBody + tableEnd );
		console.log($( '#excelTempDiv' ));
		return false;
	}
	/**
	 * 导出报表按钮单击事件执行函数
	 */
	function exportExcelBCE(){
		console.log('导出报表按钮单击事件触发……');
		
	//	console.log(currentTableBody);
//		for ( var key in currentTableHead) {
//			console.log(key)
//			console.log(currentTableHead[key])
//		}
		generStaticTable( currentTableHead, currentTableBodys );	
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
	    a.download = "分析化验设备方案数据.xls";
	    document.getElementById("aExport").click();
	    //$( '#aExport' ).click();
		return false;
	}
	
	$('#export').on('click', function(){
		exportExcelBCE();
		return false;
	
	});
	
	form.render();
	
})