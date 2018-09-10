<div class="tableTempfirst" style="width:100%;height:{{d.tableTitleHeight}};padding-top:{{d.tableTitleTop}};
		padding-bottom:{{d.tableTitleBottom}};background-color:{{d.tableTitleBgcolor}};overflow:hidden;
		{{# if( !d.isShowTitle){ }}
			display:none;
		{{# } }}
		">
	<div class='div_name' style="float:left;width:{{d.tableTitlePart1Width}};height:100%;backgroud-color:{{d.tableTitleBgcolor}};
			text-align:center;">			
		<div style="display:inline-block;margin-left:{{d.tableTitlePart1Left}};padding-left:5%;padding-right:5%;
				width:{{d.tableTitlePart1Width}};background-color:{{d.tableTitleBgcolor}};font-weight:{{d.tableTitleFontWeight}};
				text-align:center;border:{{d.tableTitlePart1Border}};color:{{d.tableTitlePart1Color}};
				font-size:{{d.tableTitlePart1Size}};line-height:27px;font-family:{{d.tableTitlePart1Family}};">
			{{d.tableTitle}}
		</div>
		{{# if(d.tableTitleMore || d.tableTitleMore.length == 0){ }}
		<a href="" style="display:inline-block;vertical-align:bottom;width:10%;
			height:{{d.tableTitleMoreHeight}};padding-left:8px;padding-right:8px;
			color:{{d.tableTitleMoreColor}};font-size:{{d.tableTitleMoreSize}};
			font-family:{{d.tableTitleMoreFamily}}">
			{{d.tableTitleMore}}			
		</a>
		{{# } }}
	</div>
	{{# if(d.tableTitleCondition[0] != ''){ }}
	<div class='div_time' style="float:right;width:{{d.tableTitlePart2Width}};height:100%; text-align:center;margin-right:{{d.tableTitlePart2Right}};">	
	    <span style="font-size:{{d.tableTitlePart2Size}};font-family:{{d.tableTitlePart2Family}};">
			{{# $.each(d.tableTitleCondition, function(index, item){ }}
				<input type="radio" name="date" value="' + {{ item }} + '" title="' + {{ item }} + '">
				<lable style="color:{{d.tableTitlePart2Color}};">{{ item }}</lable>
			{{# }) }}
	    </span>
	</div>
	{{# } }}
	<script type="text/javascript">
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