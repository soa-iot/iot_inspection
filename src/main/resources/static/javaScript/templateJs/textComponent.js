<div id="inputComponentTemplate" style="margin-left:{{d.fatherLeft}};margin-right:{{d.fatherRight}};margin-top:{{d.fatherTop}};text-align:{{d.textAlign}};
	width:{{d.fatherWidth}};height:{{d.fatherHeight}};background-color:{{d.fatherbackgroundColor}}">

	<span data-url="{{d.textUrl}}" style="color:{{d.fontColor}};margin-left:{{d.sonLeft}};
					margin-right:{{d.sonLeft}}; line-height:{{d.fatherHeight}};width:{{d.sonWidth}};
					height:{{d.sonHeight}};background-color:{{d.sonbackgroundColor}};
					font-size:{{d.fontSize}};font-color:{{d.fontColor}};font-family:{{d.fontFamily}};">
			{{d.text}}
	</span>
	<!-- 模板中写js不能使用var  不能双斜杠写注释 -->
	<script type="text/javascript">
		//显示动态数据
		var dataUrl = $('#inputComponentTemplate').find('span').attr('data-url');		
		$.ajax({
			url: dataUrl,
			async: true,
			success: function(jsonData){
				var activeData = jsonData.data;
				if($.trim(activeData) != ''){
					$('#inputComponentTemplate').find('span').text(activeData);
				}
			}
		})
	</script>
	
</div>	