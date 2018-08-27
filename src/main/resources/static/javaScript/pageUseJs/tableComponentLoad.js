/*
 * 初始化参数
 */
var global = {};//全局变量
var table = layui.table;
var layer = layui.layer;
var templ = layui.laytpl;
var form = layui.form;
var $container = $('script[layType=onlyJs]').parent();
var templateUrl = "/javaScript/templateJs/tableComponent.js";//模板地址
global.text_content_one = text_content_one;

$(function(){
	//获取组件模板
	$.ajax({ 
		type: "get",
		url: templateUrl, 
		cache: true, //默认 
		dataType: "html",//必须指定，否则根据后端判断 
		success: function(html){ 
			$container.html('');
			htmlRender(html);
		},
	})
	console.log("数据更新表格完成……");
})

/*
 * html数据渲染
 */
var htmlRender = function(html){
	console.log("开始数据渲染……");
	global[$container.attr('id')] = text_content_one;
	$.each($(html).not('script[layType=onlyJs]'), function(index, item){ 
		if($container.html()){
			templ($(item).prop("outerHTML")).render(text_content_one, function(html){
				$container.append(html);
			});	
		}else{
			templ($(item).prop("outerHTML")).render(text_content_one, function(html){
				$container.html(html);
			});
		}	
	})	
	
	//加载script内容
	$.each($(html).filter('script[layType=onlyJs]'), function(index, item){
		if($container.html()){
			$container.append(item);
		}else{
			$container.html(item);
		}	
	})			
}
