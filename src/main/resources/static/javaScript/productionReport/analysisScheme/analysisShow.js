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
				$( '#tableArea' ).append(
						'  <div style="display:inline-block"> ' + 
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
	form.render();
	
})