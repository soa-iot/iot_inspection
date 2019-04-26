$(function(){
	/**
	 * 参数初始化
	 */
	var element = layui.element
	,form = layui.form
	,laydate = layui.laydate
	,table = layui.table
	,nowDate = new Date()
	,currParamNum //当前参数的个数
	,currDayNum = 1 //当前日期控件个数
	,currCheckPos = ''  //当前分析方案巡检点
	,currPro1 = '' //当前分析项目1
	,currPro2 = []  //当前分析项目2
	,currUnit = [] //当前分析项目单位
	,currRequire = [] //当前分析项目要求
	,cellTatalWidth = 400 //数据列的总宽度
	,planName = "1"//分析化验方案的方案id（配置信息）
	,currPointName
	,currProjectName
	,pointTypeUrl = '../../../analysis/points'
	,projectTypeUrl = '../../../analysis/projects'
	,headConfigUtl = '../../../analysis/headConfig'
	,sUrl = '../../../analysis'
	,init = {
			getTableHead: function( cols ){
				//动态生成表头
				ajaxByGet( headConfigUtl, {
					"planName": planName
					,"pointName": currPointName
					,"projectName": currProjectName}, sf.showTable );			
			}
			,createValueInput: function( currObj, num, require ){
				console.log( '------动态生成输入input的个数--------' );
				console.log( currObj );
				console.log( num );
				console.log( currRequire );
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
				        		'	<option value="主体装置Ⅰ列">主体装置Ⅰ列</option>' +
				        		'	<option value="主体装置Ⅱ列">主体装置Ⅱ列</option>' +
				        		'	<option value="主体装置Ⅲ列">主体装置Ⅲ列</option>' +
				        		'	<option value="主体装置Ⅳ列">主体装置Ⅳ列</option>' +
				        		'	<option value="主体装置Ⅴ列">主体装置Ⅴ列</option>' +
				        		'	<option value="主体装置Ⅵ列">主体装置Ⅵ列</option>' +
				        		'	<option value="主体装置Ⅶ列">主体装置Ⅶ列</option>' +
				      		'	</select>' +
				  		'	</div>' +
		  			'	</div><!--'
		  		,endHtml = '--></div>'
				,midHtml = '';
				for( var z=0; z<num; z++ ){
					midHtml = midHtml + 
							'--><div class="layui-inline"  style="margin:0px;padding:0px;" > ' + 
							' 		<div class="layui-input-block" flag="paramValue"' + 
							'			 requireid="' + require[z+1].field + '" project="' + currPro2[z+1].title + '"' + 
							'			 unit="' + currUnit[z+1].title + '"' +						
							'			 style="width:' + cellTatalWidth/num + 'px;margin:0px;padding:0px"> ' + 
							' 			<input type="text" name="value" autocomplete="off" placeholder="请输入值" class="layui-input"> ' + 
							' 		</div> ' + 
							'	</div><!-- ';
				}
				currObj.append( headhtml + midHtml + endHtml );
				form.render();
				laydate.render({
			    	elem: '#time' + currDayNum,
			    	type: 'datetime',
			    	value: nowDate.getFullYear() + "-" + nowDate.getMonth() + "-" + nowDate.getDay() + " " + 
			    		   nowDate.getHours() + ":" + new Date().getMinutes() + ":" + "00",
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
					//修改当前值
					currCheckPos = data[0][1].title;
					currPro1 = data[1][1].title;
					currPro2 =  data[2] ;
					currUnit = data[3];
					currRequire = data[5];
					var num = data[data.length-1].length;
					currParamNum = num;
					init.createValueInput( currObj, num - 1, currRequire );
				}else{
					layer.msg( '请求失败', {icon:2} );
				}			
			}
			,saveValue: function( jsonData ){
				console.log( '------sf.saveValue--------' );
				if( jsonData ){
					if( jsonData.state == 0 && jsonData.data ){
						layer.msg( '保存数据成功', {icon:1} );
					}else{
						layer.msg( '保存数据失败', {icon:2} );
					}
				}else{
					layer.msg( '保存数据失败，未知错误', {icon:2} );
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
				init.createValueInput( $( '#value' ), currParamNum - 1, currRequire );
			}
			,submit: function(){
				console.log( '-------submit-------' );
				var inputValues = [], valueObjs = $( '#value' ).find( '.layui-form-item' );
				console.log( valueObjs );
				$.each( valueObjs, function( index, item ){				
					var recordtime = $ ( item ).find( 'input[id^=time]').val()
					,equipcol = $ ( item ).find( 'select' ).next().find( 'dl .layui-this' ).attr( 'lay-value' )
					,valueObj = $ ( item ).find( 'div[flag=paramValue]' );
					$.each( valueObj, function( index1, item1 ){
						var tempValue = {};
						//检查
						var currValue = $( item1 ).find( 'input' ).val();
						if( !currValue || $.trim( currValue ) == '' ){
							return true;
						}
						//获取输入值
						tempValue.recordtime = recordtime;
						tempValue.equipcol = equipcol;
						tempValue.requireid = $( item1 ).attr( 'requireid' );
						tempValue.valueunit = currCheckPos;
						tempValue.valuename = currPro1;
						tempValue.unit = $( item1 ).attr( 'unit' );
						tempValue.value = currValue;
						inputValues.push( tempValue );
					})
				})
				console.log( inputValues );
				//请求保存数据
				$.ajax({
				     type: "POST",
				     url: sUrl,
				     data: JSON.stringify( inputValues ),
				     cache: true, //默认
				     contentType: "application/json",//默认
				     dataType: "json",//必须指定，否则根据后端判断				     
				     success: sf.saveValue,
				     error:function(){
				    	 layer.msg('请求失败：');
				     }		       
				});
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
		$('#button').show();
	});
	
	form.render();

	/**
	 * 事件绑定
	 */
	$( '#add' ).on( 'click', cf.addRow );
	$( '#submit' ).on( 'click', cf.submit );
	
})