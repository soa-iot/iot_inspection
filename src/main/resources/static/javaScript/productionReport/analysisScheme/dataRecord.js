$(function(){
	/**
	 * 参数初始化
	 */
	var element = layui.element
	,form = layui.form
	,laydate = layui.laydate
	,table = layui.table
	,currParamNum //当前参数的个数
	,currDayNum = 1 //当前日期控件个数
	,cellTatalWidth = 400 //数据列的总宽度
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
			,createValueInput: function( currObj, num ){
				console.log( '------动态生成输入input的个数--------' );
				console.log( currObj );
				console.log( num );
				var headhtml = 
					'<div class="layui-form-item" style="margin:0px;padding:0px">' +
				    	'<div class="layui-inline"  style="margin:0px;padding:0px" >' +
							'<div class="layui-input-block"  style="width:150px;margin:0px;padding:0px">' +
			    				'<input type="text" style="width:150px;margin:0px" class="layui-input" id="time' + currDayNum + '" placeholder="HH:mm:ss">' +
			    			'</div>' +
					'	</div><!--' +
			    	'--><div class="layui-inline"  style="margin:0px;padding:0px">		' +				
							'<div class="layui-input-block"  style="width:150px;margin:0px;padding:0px">' +
				      		'	<select name="equipcol" lay-verify="required">' +
				        		'	<option value="1">主体装置Ⅰ列</option>' +
				        		'	<option value="2">主体装置Ⅱ列</option>' +
				        		'	<option value="3">主体装置Ⅲ列</option>' +
				        		'	<option value="4">主体装置Ⅳ列</option>' +
				        		'	<option value="5">主体装置Ⅴ列</option>' +
				        		'	<option value="6">主体装置Ⅵ列</option>' +
				        		'	<option value="7">主体装置Ⅶ列</option>' +
				      		'	</select>' +
				  		'	</div>' +
		  			'	</div><!--'
		  		,endHtml = '--></div>'
				,midHtml = '';
				for( var z=0; z<num; z++ ){
					midHtml = midHtml + 
							'--><div class="layui-inline"  style="margin:0px;padding:0px;" > ' + 
							' 		<div class="layui-input-block"  style="width:' + cellTatalWidth/num + 'px;margin:0px;padding:0px"> ' + 
							' 			<input type="text" name="value" autocomplete="off" placeholder="请输入值" class="layui-input"> ' + 
							' 		</div> ' + 
							'	</div><!-- ';
				}
				currObj.append( headhtml + midHtml + endHtml );
				form.render();
				laydate.render({
			    	elem: '#time' + currDayNum ,
			    	type: 'time',
			    	value: new Date().getHours() + ":" + new Date().getMinutes() + ":" + "00",
				})
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
					
					$( '#tableArea' ).find( 'div[class=layui-table-page]').remove();
					//动态生成值input的个数
					var currObj = $( '#value' );
					currObj.empty();
					var num = data[data.length-1].length;
					currParamNum = num;
					init.createValueInput( currObj, num - 1 );
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
			,addRow: function(){
				console.log( '-------addRow-------' );
				console.log( currParamNum );
				currDayNum ++;
				init.createValueInput( $( '#value' ), currParamNum - 1 );
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
	
	form.render();

	/**
	 * 事件绑定
	 */
	$( '#add' ).on( 'click', cf.addRow );
	
})