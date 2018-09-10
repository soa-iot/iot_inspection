	/*
	 * 设置父元素溢出样式
	 */
	var originalStyle = $currentObj.parent().attr('style');
	if(originalStyle){
		$currentObj.parent().attr('style', 'overflow:hidden;' + originalStyle);
	}else{
		$currentObj.parent().attr('style', 'overflow:hidden');
	}	
	
	/*
	 * 页面初始化
	 */
	$(function(){
		//去重（生成html中重复）
		$currentObj.parent().find('table').next('div').remove();
		$currentObj.parent().find('table').remove();
		var temp = $('<table class="layui-hide" lay-filter="table"></table>')
		.appendTo($currentObj.parent());
		//动态生成table的cols属性
		console.log(renderData.isShowOrder);
		if(renderData.isShowOrder == 'true'){
			var col = [{type: 'numbers',title: '序号', fixed: 'left',  minWidth: 80, align:'center'}];
		}else{
			var col = [];
		}
		$.each(renderData.tableHeadField, function(index, item){ 
			var tempJson = {
					field: item, title: renderData.tableHeadTitle[index], unresize:true,
					minWidth: renderData.tableHeadWidth[index], sort: false,
					align:renderData.tableHeadAlign[index], templet: '#'+item
			}
			col.push(tempJson);
		})
		console.log(col); 
		
		//发送请求加载表格
		var options = table.render({
		    elem: $currentObj.parent().find('table')[0],
		    url: renderData.tableDataUrl,
		    method: 'get',
		    //width: '100%',
		    //height:'full-20',
		    where: paramJson, 
		    totalRow: true,
		    toolbar: true,    
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
            	var $current = $currentObj.parent().find('table').next();
            	$current.css({ 'border': '0px yellow solid' });//去掉表格边框线
            	$current.css({'margin-left': renderData.tableLeft, 'margin-right': renderData.tableRight, 'margin-top': renderData.tableTop});//表格左右边距
            	$current.find('.layui-table-tool').css({'line-height': '20px','min-height': '20px', 'background-color': 'white', 'border': '0'});//设置表格工具栏
            	$current.find('.layui-table-tool .layui-table-tool-self').css({'top': '0px'});//设置表格工具栏
            	$current.find('.layui-table-tool .layui-table-tool-self .layui-inline').css({'width': '16px', 'height':'16px','padding':'0px','margin-right':'10px'});//设置表格工具栏
            	$current.find('.layui-table-cell').css( {'height': renderData.tableRowHeight , 'line-height': renderData.tableRowHeight });//修改行高	            	
            	$current.find('thead th').css( {'font-size':renderData.tableHeadFontSize, 'font-family':renderData.tableHeadFontFamily});//修改表头字体大小类型	
            	$current.find('.layui-table-main tr:even').css({ 'color': renderData.tableBodyFontColor});//修改奇数行字体颜色				
            	$current.find('.layui-table-main tr:even').css({'background-color': renderData.tableBodyEvenBackgroundColor});//修改除序号外表格内容奇数背景色
            	$current.find('.layui-table-main tr:odd').css({'background-color': renderData.tableBodyOddBackgroundColor, 'color': renderData.tableBodyFontColor});//修改除序号外表格内容偶数行背景色、字体颜色           				
            	$current.find('.layui-table-fixed tr:odd').css({'background-color': renderData.tableBodyEvenBackgroundColor});//修改序号奇数背景色
            	$current.find('.layui-table-fixed tr:even').css({'background-color': renderData.tableBodyOddBackgroundColor, 'color': renderData.tableBodyFontColor});//修改序号偶数行背景色、字体颜色
            	$current.find('.layui-table-fixed tr').css({ 'color': renderData.tableBodyFontColor});//修改序号列字体颜色	
            	$current.find('td').css({'font-size': renderData.tableBodyFontSize, 'font-family': renderData.tableBodyFontFamily});//修改表格内容的字体类型和大小
            	$current.find('.layui-table-fixed .layui-table-header span').css( {'color':  renderData.tableHeadFontColor});//修改编号的颜色
            	$current.find('.layui-table-box .layui-table-header').css({'border-width' : '0 0 0px'});//取消表头分割线
            	$current.find('thead tr').css( {'background-color': renderData.tableHeadBackgroundColor, 'color':renderData.tableHeadFontColor });//修改表头字体颜色
            	$current.find('.layui-table-page').css({'background-color':renderData.tableBodyEvenBackgroundColor, 'border': '0px yellow solid' });
            },
		    skin: 'nob' ,//表格风格
		   	even: true,//开启隔行背景
		    loading: true,
		    page: renderData.isShowPage, //是否显示分页
		    limits: [3, 4, 5, 6, 8, 10],
		    limit: 5 //每页默认显示的数量
		});	
	});	
	
	/*
	 * 列样式
	 */
	$(function(){
		//动态生成表格列的样式模板
		$.each(renderData.tableClosContentType, function(index, item){
			if(item == '1'){ 
				var tempScriptObj = document.createElement('script');
				$(tempScriptObj).attr('id', renderData.tableHeadField[index]);
				$(tempScriptObj).attr('type', 'text/html');
				$(tempScriptObj).html(
						'{{#    ' +	
						'	var fn = function(){         ' +
						'		var returnValue = 0;     ' +
						'		$.each(renderData.tableClosContentJudge, function(index, item){    ' +
						'			if(item.name == "' + renderData.tableHeadField[index] + '"){		  ' +		
						'				returnValue = index;	                  ' +					
						'			}	              ' +			
						'		});		              ' +	
						'		return returnValue;	  ' +	
				  		'	};	                      ' +	 
						'}}	                          ' +	
						'{{# 	  ' +	
						'	var num = fn();	  ' +	
						'	$.each(renderData.tableClosContentJudge[num].value, function(index1, item1){  	  ' +	
						'}}	      ' +	
						'	{{#   if(d.' + renderData.tableHeadField[index] + ' == item1) { }}	  ' +	
				    	'		<span style="color:{{renderData.tableClosContentColor[num].value[index1]}}">	  ' +	
						'			 {{ d.' + renderData.tableHeadField[index] + '}}	  ' +	
						'		</span>	  ' +	
						'	{{#  }  }}	  ' +	
						'{{#  }) }}	  ' 	
				).appendTo($currentObj.parent());
			} else if(item == '2'){
				var tempScriptObj = document.createElement('script');
				$(tempScriptObj).attr('id', renderData.tableHeadField[index]);
				$(tempScriptObj).attr('type', 'text/html');
				$(tempScriptObj).html(
						'{{#    ' +	
						'	var fn = function(){         ' +
						'		var returnValue = 0;     ' +
						'		$.each(renderData.tableClosContentJudge, function(index, item){    ' +
						'			if(item.name == "' + renderData.tableHeadField[index] + '"){		  ' +		
						'				returnValue = index;	                  ' +					
						'			}	              ' +			
						'		});		              ' +	
						'		return returnValue;	  ' +	
				  		'	};	                      ' +	 
						'}}	                          ' +	
						'{{# 	  ' +	
						'	var num = fn(); ' +	
						'	$.each(renderData.tableClosContentJudge[num].value, function(index1, item1){  	  ' +	
						'}}	      ' +	
						'	{{#   if(d.' + renderData.tableHeadField[index] + ' == item1) { }}	  ' +	
						'		<span style="background-color:{{ renderData.tableClosContentColor[num].value[index1] }};	  ' +
						'			 color: {{ renderData.tableBodyFontColor }};padding:5px 10px 5px 10px;">	  ' +	
						'			 {{ d.' + renderData.tableHeadField[index] + '}}	  ' +	
						'		</span>	  ' +
						'	{{#  }  }}	  ' +	
						'{{#  }) }}	  ' 	
				).appendTo($currentObj.parent());
			}
		});
		
		/*
		 * 表格高度自适应
		 */
		var fatherHeight = parseInt($currentObj.parent()[0].offsetHeight);
		var brotherHeight = parseInt($currentObj.parent().find('div :first')[0].offsetHeight);
		var oldThisDivStyle = $currentObj.parent().find('div:first').next('div').attr('style');
		$currentObj.parent().find('div:first').next('div').attr(
			'style', oldThisDivStyle + ";height:" + (fatherHeight - brotherHeight) + 'px'
		);
	})