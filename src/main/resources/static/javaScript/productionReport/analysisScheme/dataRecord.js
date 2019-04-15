$(function(){
	/**
	 * 参数初始化
	 */
	var element = layui.element
	,form = layui.form
	,laydate = layui.laydate
	,table = layui.table
	,planName = "1"//分析化验方案的方案id（配置信息）
	,currPointName
	,currProjectName
	,pointTypeUrl = '../../../analysis/points'
	,projectTypeUrl = '../../../analysis/projects'
	,headConfigUtl = '../../../analysis/headConfig'
	,init = {
			getTableHead: function( cols ){
				//动态生成表头
				ajaxByGet( headConfigUtl, {
					"planName": planName
					,"pointName": currPointName
					,"projectName": currProjectName}, sf.showTable );			
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
									'<input type="radio" name="project" value="' 
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
				if( jsonData ){
					var data = jsonData.data;
					if( data && jsonData.state ==0 ){
						//生成表格
						table.render({
							elem: '#table'
						    ,url: ''
							,cols: data
						    ,page: true
						    , done : function( res , curr , count ){
						    	consol.log('11111111');
						    	$( '#tableArea').find( '.layui-table-page' ).remove();
						    	$( '#tableArea').find( 'div .layui-form ').css({"padding":"0px"});
						    }
						});
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
	form.on( 'radio(project)', function( data ){
		currProjectName = data.value;
		init.getTableHead();
	});
	
	//事件空间渲染
	laydate.render({
    	elem: '#time',
    	type: 'time',
    	value: new Date().getHours() + ":" + new Date().getMinutes() + ":" + "00",
	})
})