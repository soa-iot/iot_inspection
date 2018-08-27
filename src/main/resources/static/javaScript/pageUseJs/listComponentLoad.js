/*
 * 初始化参数
 */
var element = layui.element;
var $container = $('#inputListComponent1');//组件容器对象
var templateUrl = "/javaScript/templateJs/listComponent.js";//模板地址
var elemNum ;//元素个数

$(function(){
	//如果数据url不为空则更新数据
	var dataUrl = renderData.textUrl.second;
	if(dataUrl){
		renderData.text = [];
		ajaxByGet(dataUrl, {}, function(jsonData){
			if(jsonData != null ){
				var data = jsonData.data;
				if(jsonData.state == '0' && data !=null ){
					renderData.text = data;
					console.log("renderData.text数据初始化完成……");
					console.log(renderData.text);
				}else{
					layer.msg('请求数据失败',{icon:6});
				}					
			}else{
				layer.msg('请求数据不存在',{icon:2});
			}
		}, false);
	}	
	
	//修改更新数据renderData.text
	var colorStateArr = renderData.colorState.split(",");
	var colorArr = renderData.color.split(",");
	var pictureArr = renderData.picture.split(",");						
	$.each(renderData.text, function(index, item){ 
		var num = item.stateColor;
		item.url = pictureArr[num];
		item.color = colorArr[num];
	})
					
	console.log("json数据更新完成……");
	console.log(renderData);
	
	//获取组件模板\
	$.ajax({ 
		type: "get",
		url: templateUrl, 
		cache: false, //默认 
		dataType: "html",//必须指定，否则根据后端判断 
		success: function(html){ 
			console.log("成功获取html模板……");
			htmlRender(html);
		},
	})
})

/*
 * html数据渲染
 */
var htmlRender = function(html){ 
	console.log(renderData);	
	console.log("开始数据刷新html……");
	layui.laytpl(html).render(renderData, function(html){
		$container.html(html); 
	});
	console.log(renderData);	
	console.log("数据刷新完毕……");
}