<script type="text/javascript">	
	
</script>
<div id="inputListComponent1" style="width:100%;height:100%;background-color:{{d.grandbackgroundColor}}">
	<ul style="width:100%;height:100%;position:absolute">
	{{# $.each(d.text, function(index, item){ }}
		<li style="float:left;position:relative;width:{{d.fatherWidth}};height:{{d.fatherHeight}};margin-right:{{d.fatherRight}};
				left:{{d.left}};top:{{d.top}};margin-top:{{d.fatherTop}};margin-left:{{d.fatherLeft}};background-color:{{item.color}};" >
			<div style="width:100%;height:100%;background-color:{{d.fatherbackgroundColor}};border:{{d.borderLine}}">	
				<div style="width:{{d.sonWidth.first}};height:100%;text-align:center;vertical-align:middle;display:inline-block;background-color:{{ d.part1BackgroundColor[index] }}">	
					<span data-url="{{d.textUrl.first}}" style="color:{{d.fontColor.first}};margin-left:{{d.sonLeft.first}};
							margin-right:{{d.sonRight.first}};line-height:100%;font-size:{{d.fontSize.first}};
							background-color:{{d.sonbackgroundColor.first}};
							font-family:{{d.fontFamily.first}};">													
						{{item.first}}										
					</span>
				</div><!--  
			 --><div style="width:{{d.sonWidth.second}};height:100%;text-align:center;vertical-align:middle;display:inline-block;background-color:{{ d.part2BackgroundColor[index] }}">
					<span data-url="{{d.textUrl.second}}" style="color:{{d.fontColor.second}};margin-left:{{d.sonLeft.second}};
							margin-right:{{d.sonRight.second}};line-height:100%;font-size:{{d.fontSize.second}};
							background-color:{{d.sonbackgroundColor.second}};
							font-family:{{d.fontFamily.second}};">
						{{# if(item.stateValue == "0"){ }}
							{{item.second}}
						{{# }else if(item.stateValue == "1"){ }}
							<img style="width:15px;height:15px;" alt="图片" src="{{item.url}}">													
						{{# }else{ }}
						{{# } }}
					</span>	
				</div>			
			</div>	
		</li>
	{{# }) }}
	</ul>
	<script type="text/javascript" layType="onlyJS">	
		$(function(){
			$.each($('#inputListComponent1').find('span'), function(index, item){
				var liHeight = $(item).closest('li')[0].offsetHeight;
				var style = $(item).attr('style');
				$(item).attr('style', style + ';line-height:'+ liHeight + 'px');																		
			})	
		})												
	</script>
</div>