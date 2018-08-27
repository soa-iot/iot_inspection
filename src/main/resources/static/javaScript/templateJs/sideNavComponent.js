<aside class='layui-sideNav-aside' style="background-color:{{ d.backgroundColor.first }};
			  width:{{ d.asideSize.width }}; height:{{ d.asideSize.height }}"> 			
	<ul>
		{{# $.each(d.treeList, function(index, item){  }}
		<li onmouseover="sideNavMouseOverEvent(this)" 
			onmouseout="sideNavMouseOutEvent(this)"
			onclick="sideNavClickEvent(this)"
			data-url="{{ item.firstIframeUrl }}">
			<figure>
				<img src="{{ item.firstPicUrl }}" style="width:{{ d.imgSize }}">
				<figcaption style="font-size:{{ d.fontSize.first }};
								   color:{{ d.fontColor.first }};
								   font-family:{{ d.fontFamily.first }}">
					{{ item.firstName }}
				</figcaption>
			</figure>							
			<ul style="background-color:{{ d.backgroundColor.second }};
					   width: {{ d.secondMenuTotalWidth }};">
				{{# $.each(item.secondName, function(index1, item1){ }}
				<li style="width:{{d.secondMenuSize.width}}; 
						   height:{{d.secondMenuSize.height}}"
					data-url="{{ item.secondIframeUrl[index1] }}"
					onmouseover="secondSideNavMouseOverEvent(this)"
					onmouseout="secondSideNavMouseOutEvent(this)" >
					<a href="#" style="font-size:{{ d.fontSize.second }};
									   color:{{ d.fontColor.second }};
									   line-height:{{ d.secondMenuSize.height }};
									   font-family:{{ d.fontFamily.second }}">
						{{ item1 }}
					</a>
				</li>
				{{# }) }}	
			</ul>
		</li>
	{{# }) }}	
	</ul>
</aside>
<div class='layui-sideNav-div' style="width:{{d.iframeDivSize.width}} ; height:{{d.iframeDivSize.height}};" +
		"border:0px red solid;overflow-x: hidden; overflow-y: hidden;">
	<iframe src="{{ d.iframeUrl }}"  >				
	</iframe>	
</div>	
<script type="text/javascript" layType="needLayuiTempl">
	function sideNavMouseOverEvent(thisObj){
		$(thisObj).css('background-color', '{{d.hoverColor.first}}' );
	}
	function sideNavMouseOutEvent(thisObj){
		$(thisObj).css('background-color', '{{d.backgroundColor.first}}' );			
	}
	
	function secondSideNavMouseOverEvent(thisObj){
		$(thisObj).css('background-color', '{{d.hoverColor.second}}' );
	}
	
	function secondSideNavMouseOutEvent(thisObj){
		$(thisObj).css('background-color', '{{d.backgroundColor.second}}' );
	}
	
	function sideNavClickEvent(thisObj){
		var tabUrl = $(thisObj).attr('data-url');
		$(thisObj).parents('aside').next().find('iframe').attr('src', tabUrl);
	}			
</script>