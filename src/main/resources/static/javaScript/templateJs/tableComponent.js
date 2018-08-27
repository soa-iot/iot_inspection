<script type="text/javascript"  layType="onlyJs">
	/*
	 * 初始化参数
	 */
	var global = {};//全局变量
	var table = layui.table;
	var layer = layui.layer;
	var templ = layui.laytpl;
	var form = layui.form;
	var $container =$($('script')[$('script').length-1]).parent();
	var templateUrl = "/javaScript/templateJs/tableComponent.js";//模板地址
	global[$container.attr('id')] = text_content_one;
</script>
<div class="tableTempfirst" style="width:100%;height:{{d.tableTitleHeight}};padding-top:{{d.tableTitleTop}};
		padding-bottom:{{d.tableTitleBottom}};background-color:{{d.tableTitleBgcolor}};overflow:hidden;
		{{# if( !d.isShowTitle){ }}
			display:none;
		{{# } }}
		">
	<div class='div_name' style="float:left;width:40%;height:100%;backgroud-color:{{d.tableTitleBgcolor}};
			text-align:center;">			
		<div style="display:inline-block;margin-left:{{d.tableTitlePart1Left}};padding-left:5%;padding-right:5%;
				width:{{d.tableTitlePart1Width}};background-color:{{d.tableTitleBgcolor}};
				text-align:center;border:{{d.tableTitlePart1Border}};color:{{d.tableTitlePart1Color}};
				font-size:{{d.tableTitlePart1Size}};line-height:27px;font-family:{{d.tableTitlePart1Family}}">
			{{d.tableTitle}}
		</div><!--
		--><a href="" style="display:inline-block;vertical-align:bottom;width:10%;
				height:{{d.tableTitleMoreHeight}};padding-left:8px;padding-right:8px;
				color:{{d.tableTitleMoreColor}};font-size:{{d.tableTitleMoreSize}};
				font-family:{{d.tableTitleMoreFamily}}">
				{{d.tableTitleMore}}			
			</a>
	</div>
	<div class='div_time' style="float:right;width:{{d.tableTitlePart2Width}};height:100%; text-align:center;margin-right:{{d.tableTitlePart2Right}};">	
	    <span style="font-size:{{d.tableTitlePart2Size}};font-family:{{d.tableTitlePart2Family}};">
			{{# $.each(d.tableTitleCondition, function(index, item){ }}
				<input type="radio" name="date" value="' + {{ item }} + '" title="' + {{ item }} + '">
				<lable style="color:{{d.tableTitlePart2Color}};">{{ item }}</lable>
			{{# }) }}
	    </span>
	</div>
	<script type="text/javascript" >
		console.log('设置表格标题的上下居中样式……');
		var $fatherDiv = $('.tableTempfirst').find('.div_name');
		var fatherHeight = $fatherDiv[0].offsetHeight; 
		var thisHeight = $fatherDiv.find('div')[0].offsetHeight; 
		var thisMarginTop = (parseInt(fatherHeight) - parseInt(thisHeight)) / 2; 
		var oldStyleNameStr = $fatherDiv.find('div').attr('style');
		var newStyleNameStr = oldStyleNameStr + ';margin-top:' + thisMarginTop + 'px';
		$fatherDiv.find('div').attr('style', newStyleNameStr);
		
		var oldStyleTimeStr = $fatherDiv.next().find('span').attr('style');
		var newStyleTimeStr = oldStyleTimeStr + ';line-height:' + fatherHeight + 'px';
		$fatherDiv.next().find('span').attr('style', newStyleTimeStr);
	</script>
</div>

<script type="text/javascript" layType="onlyJs">	
	var currentObj = $('script')[$('script').length-1];
	
	//设置父元素溢出样式
	var originalStyle = $(currentObj).parent().attr('style');
	if(originalStyle){
		$(currentObj).parent().attr('style', 'overflow:hidden;' + originalStyle);
	}else{
		$(currentObj).parent().attr('style', 'overflow:hidden');
	}	
	
	/*
	 * 页面初始化
	 */
	$(function(){
		//去重（生成html中重复）
		$(currentObj).parent().find('table').next('div').remove();
		$(currentObj).parent().find('table').remove();
		var temp = $('<table class="layui-hide" id="table" lay-filter="table"></table>')
		.appendTo($(currentObj).parent());
		console.log(global);
		//动态生成table的cols属性
		var col = [{type: 'numbers',title: '序号', fixed: 'left',  minWidth: 80, align:'center'}];
		$.each(global[$(currentObj).parent().attr('id')].tableHeadField, function(index, item){ 
			var tempJson = {
					field: item, title: global[$(currentObj).parent().attr('id')].tableHeadTitle[index], unresize:true,
					minWidth: global[$(currentObj).parent().attr('id')].tableHeadWidth[index], sort: false,
					align:global[$(currentObj).parent().attr('id')].tableHeadAlign[index], templet: '#'+item
			}
			col.push(tempJson);
		})
		console.log(col); 
		
		//发送请求加载表格
		var options = table.render({
		    elem: '#table',
		    url: global[$(currentObj).parent().attr('id')].tableDataUrl,
		    method: 'get',
		    //width: '100%',
		    //height:'full-20',
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
            	var $current = $(currentObj).parent().find('table').next();
            	$current.css({ 'border': '0px yellow solid' });//去掉表格边框线
            	$current.css({'margin-left': global[$(currentObj).parent().attr('id')].tableLeft, 'margin-right': global[$(currentObj).parent().attr('id')].tableRight, 'margin-top': global[$(currentObj).parent().attr('id')].tableTop});//表格左右边距
            	$current.find('.layui-table-cell').css( {'height': global[$(currentObj).parent().attr('id')].tableRowHeight , 'line-height': global[$(currentObj).parent().attr('id')].tableRowHeight });//修改行高	            	
            	$current.find('thead th').css( {'font-size':global[$(currentObj).parent().attr('id')].tableHeadFontSize, 'font-family':global[$(currentObj).parent().attr('id')].tableHeadFontFamily});//修改表头字体大小类型	
            	$current.find('.layui-table-main tr:even').css({ 'color': global[$(currentObj).parent().attr('id')].tableBodyFontColor});//修改奇数行字体颜色				
            	$current.find('.layui-table-main tr:even').css({'background-color': global[$(currentObj).parent().attr('id')].tableBodyEvenBackgroundColor});//修改除序号外表格内容奇数背景色
            	$current.find('.layui-table-main tr:odd').css({'background-color': global[$(currentObj).parent().attr('id')].tableBodyOddBackgroundColor, 'color': global[$(currentObj).parent().attr('id')].tableBodyFontColor});//修改除序号外表格内容偶数行背景色、字体颜色           				
            	$current.find('.layui-table-fixed tr:odd').css({'background-color': global[$(currentObj).parent().attr('id')].tableBodyEvenBackgroundColor});//修改序号奇数背景色
            	$current.find('.layui-table-fixed tr:even').css({'background-color': global[$(currentObj).parent().attr('id')].tableBodyOddBackgroundColor, 'color': global[$(currentObj).parent().attr('id')].tableBodyFontColor});//修改序号偶数行背景色、字体颜色
            	$current.find('.layui-table-fixed tr').css({ 'color': global[$(currentObj).parent().attr('id')].tableBodyFontColor});//修改序号列字体颜色	
            	$current.find('td').css({'font-size': global[$(currentObj).parent().attr('id')].tableBodyFontSize, 'font-family': global[$(currentObj).parent().attr('id')].tableBodyFontFamily});//修改表格内容的字体类型和大小
            	$current.find('.layui-table-fixed .layui-table-header span').css( {'color':  global[$(currentObj).parent().attr('id')].tableHeadFontColor});//修改编号的颜色
            	$current.find('.layui-table-box .layui-table-header').css({'border-width' : '0 0 0px'});//取消表头分割线
            	$current.find('thead tr').css( {'background-color': global[$(currentObj).parent().attr('id')].tableHeadBackgroundColor, 'color':global[$(currentObj).parent().attr('id')].tableHeadFontColor });//修改表头字体颜色
            	$current.find('.layui-table-page').css({'background-color':global[$(currentObj).parent().attr('id')].tableBodyEvenBackgroundColor, 'border': '0px yellow solid' });
            },
		    skin: 'nob' ,//表格风格
		   	even: true,//开启隔行背景
		    loading: true,
		    page: global[$(currentObj).parent().attr('id')].isShowPage, //是否显示分页
		    limits: [3, 4, 5, 6, 8, 10],
		    limit: 5 //每页默认显示的数量
		});	
	});		
</script>
<script type="text/javascript"  layType="onlyJs">	
	var currentObj = $('script')[$('script').length-1];
	$(function(){
		var tempData = global[$(currentObj).parent().attr('id')];
		//动态生成表格列的样式模板
		$.each(global[$(currentObj).parent().attr('id')].tableClosContentType, function(index, item){
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
				).appendTo($(currentObj).parent());
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
				).appendTo($(currentObj).parent());
			}
		});
		
		/*
		 * 表格高度自适应
		 */
		var fatherHeight = parseInt($(currentObj).parent()[0].offsetHeight);
		var brotherHeight = parseInt($(currentObj).parent().find('div :first')[0].offsetHeight);
		var oldThisDivStyle = $(currentObj).parent().find('div:first').next('div').attr('style');
		$(currentObj).parent().find('div:first').next('div').attr(
			'style', oldThisDivStyle + ";height:" + (fatherHeight - brotherHeight) + 'px'
		);
		
		/*
		 * 选择条件，更新表格
		 */
		
		
		/*
		 * 点击more,展示全部表格
		 */
	})
</script>
