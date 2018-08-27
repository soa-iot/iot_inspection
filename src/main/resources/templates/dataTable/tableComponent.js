<script type="text/javascript" id='tableScript' layType="onlyJs">		
	$('<table class="layui-hide" id="table" lay-filter="table"></table>')
		.appendTo($('#tableScript').parent());
	
	$(function(){
		//动态生成table的cols属性
		var col = [{type: 'numbers',title: '序号', fixed: 'left',  minWidth: 80, align:'center'}];
		$.each(global[$('#tableScript').parent().attr('id')].tableHeadField, function(index, item){ 
			var tempJson = {
					field: item, title: global[$('#tableScript').parent().attr('id')].tableHeadTitle[index], unresize:true,
					minWidth: global[$('#tableScript').parent().attr('id')].tableHeadWidth[index], sort: false,
					align:global[$('#tableScript').parent().attr('id')].tableHeadAlign[index], templet: '#'+item
			}
			col.push(tempJson);
		})
		console.log(col); 
		
		//发送请求加载表格
		var options = table.render({
		    elem: '#table',
		    url: global[$('#tableScript').parent().attr('id')].tableDataUrl,
		    method: 'get',
		    //width: '100%',
		    height: 150,
		    where: {}, 
		    request: {
	    		pageName: 'curr', //页码的参数名称，默认：page
	    		limitName: 'size' //每页数据量的参数名，默认：limit
	    	},
	    	response: {
				statusName: 'state', //数据状态的字段名称，默认：code
				statusCode: 0 ,//成功的状态码，默认：0
				msgName: 'message', //状态信息的字段名称，默认：msg
				countName: 'total', //数据总数的字段名称，默认：count
				dataName: 'data'//数据列表的字段名称，默认：data
			},      				
		    cols: [ col ],
            done: function (res, curr, count) { 
            	var $current = $('#tableScript').parent().find('table').next();
            	$current.css({ 'border': '0px yellow solid' });//去掉表格边框线
            	$current.css({'margin-left': global[$('#tableScript').parent().attr('id')].tableLeft, 'margin-right': global[$('#tableScript').parent().attr('id')].tableRight, 'margin-top': global[$('#tableScript').parent().attr('id')].tableTop});//表格左右边距
            	$current.find('.layui-table-cell').css( {'height': global[$('#tableScript').parent().attr('id')].tableRowHeight , 'line-height': global[$('#tableScript').parent().attr('id')].tableRowHeight });//修改行高	            	
            	$current.find('thead th').css( {'font-size':global[$('#tableScript').parent().attr('id')].tableHeadFontSize, 'font-family':global[$('#tableScript').parent().attr('id')].tableHeadFontFamily});//修改表头字体大小类型	
            	$current.find('tr:even:not(:first)').css({ 'color': global[$('#tableScript').parent().attr('id')].tableBodyFontColor});//修改奇数背景色、字体颜色				
            	$current.find('tr:even').css({'background-color': global[$('#tableScript').parent().attr('id')].tableBodyEvenBackgroundColor});//修改奇数背景色
            	$current.find('tr:odd').css({'background-color': global[$('#tableScript').parent().attr('id')].tableBodyOddBackgroundColor, 'color': global[$('#tableScript').parent().attr('id')].tableBodyFontColor});//修改偶数行背景色、字体颜色
            	$current.find('td').css({'font-size': global[$('#tableScript').parent().attr('id')].tableBodyFontSize, 'font-family': global[$('#tableScript').parent().attr('id')].tableBodyFontFamily});//修改表格内容的字体类型和大小
            	$current.find('.layui-table-fixed .layui-table-header span').css( {'color':  global[$('#tableScript').parent().attr('id')].tableHeadFontColor});//修改编号的颜色
            	$current.find('.layui-table-box .layui-table-header').css({'border-width' : '0 0 0px'});//取消表头分割线
            	$current.find('thead tr').css( {'background-color': global[$('#tableScript').parent().attr('id')].tableHeadBackgroundColor, 'color':global[$('#tableScript').parent().attr('id')].tableHeadFontColor });//修改表头字体颜色
            	$current.find('.layui-table-page').css({'background-color':global[$('#tableScript').parent().attr('id')].tableBodyEvenBackgroundColor, 'border': '0px yellow solid' });
            },
		    skin: 'nob' ,//表格风格
		   	even: true,//开启隔行背景
		    loading: true,
		    page: global[$('#tableScript').parent().attr('id')].isShowPage, //是否显示分页
		    limits: [3, 4, 5, 6, 8, 10],
		    limit: 5 //每页默认显示的数量
		});	
	});		
</script>
<script type="text/javascript" id='tableScriptTemplate' layType="onlyJs">		
	var tempData = global[$('#tableScript').parent().attr('id')];
	//动态生成表格列的样式模板
	$.each(global[$('#tableScript').parent().attr('id')].tableClosContentType, function(index, item){
		if(item == '1'){ 
			var tempScriptObj = document.createElement('script');
			$(tempScriptObj).attr('id', tempData.tableHeadField[index]);
			$(tempScriptObj).attr('type', 'text/html');
			$(tempScriptObj).html(
					'{{#    ' +	
					'	var fn = function(){         ' +
					'		var returnValue = 0;     ' +
					'		$.each(tempData.tableClosContentJudge, function(index, item){    ' +
					'			if(item.name == "' + tempData.tableHeadField[index] + '"){		  ' +		
					'				returnValue = index;	                  ' +					
					'			}	              ' +			
					'		});		              ' +	
					'		return returnValue;	  ' +	
			  		'	};	                      ' +	 
					'}}	                          ' +	
					'{{# 	  ' +	
					'	var num = fn();	  ' +	
					'	$.each(tempData.tableClosContentJudge[num].value, function(index1, item1){  	  ' +	
					'}}	      ' +	
					'	{{#   if(d.' + tempData.tableHeadField[index] + ' == item1) { }}	  ' +	
			    	'		<span style="color:{{tempData.tableClosContentColor[num].value[index1]}}">	  ' +	
					'			 {{ d.' + tempData.tableHeadField[index] + '}}	  ' +	
					'		</span>	  ' +	
					'	{{#  }  }}	  ' +	
					'{{#  }) }}	  ' 	
			).appendTo($('#tableScript').parent());
		} else if(item == '2'){
			var tempScriptObj = document.createElement('script');
			$(tempScriptObj).attr('id', tempData.tableHeadField[index]);
			$(tempScriptObj).attr('type', 'text/html');
			$(tempScriptObj).html(
					'{{#    ' +	
					'	var fn = function(){         ' +
					'		var returnValue = 0;     ' +
					'		$.each(tempData.tableClosContentJudge, function(index, item){    ' +
					'			if(item.name == "' + tempData.tableHeadField[index] + '"){		  ' +		
					'				returnValue = index;	                  ' +					
					'			}	              ' +			
					'		});		              ' +	
					'		return returnValue;	  ' +	
			  		'	};	                      ' +	 
					'}}	                          ' +	
					'{{# 	  ' +	
					'	var num = fn(); ' +	
					'	$.each(tempData.tableClosContentJudge[num].value, function(index1, item1){  	  ' +	
					'}}	      ' +	
					'	{{#   if(d.' + tempData.tableHeadField[index] + ' == item1) { }}	  ' +	
					'		<span style="background-color:{{ tempData.tableClosContentColor[num].value[index1] }};	  ' +
					'			 color: {{ tempData.tableBodyFontColor }};padding:5px 10px 5px 10px;">	  ' +	
					'			 {{ d.' + tempData.tableHeadField[index] + '}}	  ' +	
					'		</span>	  ' +
					'	{{#  }  }}	  ' +	
					'{{#  }) }}	  ' 	
			).appendTo($('#tableScript').parent());
		}
	});
</script>