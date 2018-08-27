/**
 * 参数初始化
 */
var element = layui.element;
/*
 * 组件容器对象
 */
var $container = $('#text_content_one');
/*
 * 模板地址
 */
var templateUrl = "/javaScript/templateJs/listComponent.js";
/*
 * 元素个数
 */
var elemNum ;
/*
 * 数据链接input是否被选中
 */
var isChooesdDataUrlInput = false;

/*
 * 组件配置数据
 */
var renderData = {                  			
	text: [],
	textUrl: "/html/analogData/listComponentsActiveData.html",	
	
	sonbackgroundColor: [
		{first: "", second: ""},
		{first: "", second: ""},
		{first: "", second: ""}
	], 
	grandbackgroundColor: "#00104E",
	fatherbackgroundColor: "",
	part1BackgroundColor: ["","",""],
	part2BackgroundColor: ["","",""],
	
	borderLine: "",
	left: "1%",
	top: "1%",
	fatherLeft: "1%",
	fatherTop: "2%",
	fatherRight: "1%",
	fatherWidth: "14%",
	fatherHeight: "10%",				
	fatherPosition: "center",
					
	sonLeft: {first: "3px", second: "0px"},
	sonRight: {first: "3px", second: ""},
	sonWidth: {first: "70%", second: "30%"},
	sonHeight: {first: "30px", second: "30px"},					
	fontSize: {first: "12px", second: "12px"},
	fontFamily: {first: "华文楷体", second: "宋体"},
	fontColor: {first: "white", second: "white"},
	
	stateValue: "0,1",
	colorState: "0,1,2,3",
	color: "#0021B8,#D8A903,#FD3100,#8E2CBD",
	picture: "/picture/logo.png,/picture/test.png,/picture/test1.png,/picture/test2.png"
}
	
/**
 * html页面初始化
 */
$(function(){
	/*
	 * 初始化请求数据		
	 */
	getComponentData();	
	
	/*
	 * 初始化请求组件模板
	 */
	var componentHtml = getComponentTemplate();
	
	/*
	 * html初始化渲染
	 */
	if(componentHtml){
		htmlRender(componentHtml);
	}	
});

/**
 * 事件绑定
 */
$(function(){	 
	/*
	 * 所有输入框blur事件绑定
	 */
	$('input[type=text]').on('blur', inputBlurEvent);
	
	/*
	 * 数据链接Input的click事件绑定
	 */
	$('input[name=textUrl]').on('click', dataUrlInputClickEvent);
	
	/*
	 * 导出html按钮单击事件绑定saveButtonClickEvent
	 */
	$('#text_content_two_button button').on('click', buttonClickEvent);
	
	/*
	 * 导出html按钮单击事件绑定
	 */
	$('#text_content_two_one_button button').on('click', saveButtonClickEvent);
	
	/*
	 * 表单提交验证事件绑定
	 */
	layui.form.on('submit(save)', function(data){	
		//更新json数据
		$.each($('input[type=text]'), function(index, item){
			var tempName = $(item).attr('name');	
			var itenValue = $(item).val();					
			if(itenValue == null && itenValue == ''){
				return true;
			}
			
			//定制textUrl的input
			if($(item).attr('name') == 'textUrl' && isChooesdDataUrlInput){
				textUrl = itenValue;
				//获取和设置后端获取的动态数据
				getSetActiveData();
				isChooesdDataUrlInput = false;
				return false;
			}
			
			//定制setWidtht的input
			if($(item).attr('name') == 'setWidth'){
				var $page_html = $('.page_html');
				var setwidth = $(item).val();
				var originalStyle = $page_html.attr('style');
				if(originalStyle){
					$page_html.attr('style', originalStyle + 'width:' + setwidth + 'px;' );
				}else{ 
					$page_html.attr('style', 'width:' + setwidth + 'px;');
				}	
				return true;
			}
			
			//定制setHeight的input
			if($(item).attr('name') == 'setHeight'){
				var $page_html = $('.page_html');
				var setHeight = $(item).val();
				var originalStyle = $page_html.attr('style');
				if(originalStyle){
					$page_html.attr('style', originalStyle + 'height:' + setHeight + 'px;');
				}else{
					$page_html.attr('style', 'height' + setHeight + 'px;');
				}	
				return true;
			}			
			
			//判断属性值是否转换
			if(contains(tempName, "_")){ 
				var flag = "";
				//属性名（数组）_元素
				var elemArr = tempName.split("_"); 
				
				var contentArr = [];						
				if(contains(itenValue, ',')){
					contentArr = itenValue.split(",");
				}else if(contains(itenValue, '.')){
					contentArr = itenValue.split(".");
				}else{
					contentArr = itenValue.split("，");
				}
				
				//清空赋值		
				console.log(elemArr[1]);
				$.each(contentArr, function(index, item){ 
					if(elemArr[1] == 'first'){
						renderData[elemArr[0]][index] = {
								first: "", 
								second: "", 
								stateValue:"", 
								stateColor:"", 
								url:"", 
								color:""
						};
					}									
					console.log(renderData[elemArr[0]][index]);
					console.log(renderData[elemArr[0]][index][elemArr[1]]);
					renderData[elemArr[0]][index][elemArr[1]] = item;							
				})	
				if(renderData[elemArr[0]].length > contentArr.length){
					renderData[elemArr[0]].splice(
							contentArr.length, renderData[elemArr[0]].length - contentArr.length);
				}								
				return true;
			}else if(contains(tempName, "-")  ){ 
				//属性名-元素,以Arr结尾
				var contentArr = new Array();
				if(contains(itenValue, ',')){
					contentArr = itenValue.split(",");
				}else if(contains(itenValue, '.')){
					contentArr = itenValue.split(".");
				}else{
					contentArr = itenValue.split("，");
				}	
				
				var elemArr = tempName.split("-");
				renderData[elemArr[0]] = [];
				renderData[elemArr[0]] = contentArr;
				return true;
			}else if(contains(tempName, "+")){
				var elemArr = tempName.split("+");	
				renderData[elemArr[0]][elemArr[1]] = itenValue;
				return true;
			}else{
				
			}			
			
			var tempType = $(item).attr('name');
			renderData[tempType] = $(item).val();
		})
		//如果数据url不为空则更新数据
		/*
		var dataUrl = renderData.textUrl;
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
		*/
		//修改更新数据renderData.text
		var colorStateArr = renderData.colorState.split(",");
		var colorArr = renderData.color.split(",");
		var pictureArr = renderData.picture.split(",");	 
		var stateValueArr = renderData.stateValue.split(",");	 
		if(renderData.text){
			$.each(renderData.text, function(index, item){ 				
				var num = $.trim(colorStateArr.indexOf($.trim(item.stateColor)));	
				item.url = pictureArr[num];
				item.color = colorArr[num];
				item.stateValue = stateValueArr[num];
			})
		}
						
		console.log("json数据更新完成……");
		console.log(renderData);
		
		//获取组件模板\
		$.ajax({ 
			type: "get",
			url: templateUrl, 
			cache: true, 
			dataType: "html",
			success: function(html){ 
				htmlRender(html);
			},
		})
		return false; 
	});
	
	//重置按钮事件绑定
	$('button[type=reset]').on('click', resetButtonClickEvent);
});
		
/**
 * html数据渲染
 */
var htmlRender = function(html){ 
	console.log("数据渲染html开始……");
	layui.laytpl(html).render(renderData, function(html){
		$container.html(html); 
		console.log("数据渲染html成功……");
	});
}

/**
 * 初始化请求数据回调函数
 */
var getComponentData = function(jsonData){
	if(renderData.textUrl){
		ajaxByGet(renderData.textUrl, {}, successGetData, false);
	}	
	console.log(renderData.text);
}

var successGetData = function(jsonData){
	if(jsonData){
		var data = jsonData.data;
		if(jsonData.state == 0 && data){
			renderData.text = [];
			$.each(data, function(index, item){
				//初始化renderData
				renderData.text[index] = {};
				
				//赋值colr、stateValue、 url、stateColor
				renderData.text[index].stateColor = item.status;
				renderData.text[index].first = item.deviceId;
				renderData.text[index].second = item.value;
				if(item.status == 0){
					renderData.text[index].stateValue = '0';
					renderData.text[index].color = "#001CC2";
				}else if(item.status == 1){
					renderData.text[index].stateValue = '1';
					renderData.text[index].color = "#D8A903";
					renderData.text[index].url = "/picture/test1.png";
				}else if(item.status == 2){
					renderData.text[index].stateValue = '1';
					renderData.text[index].color = "#8E2CBD";
					renderData.text[index].url = "/picture/test2.png";
				}else if(item.status == 3){
					renderData.text[index].stateValue = '1';
					renderData.text[index].color = "#FD3100";
					renderData.text[index].url = "/picture/test.png";
				}else{
					renderData.text[index].stateValue = '1';
					renderData.text[index].color = "#8E2CBD";
					renderData.text[index].url = "/picture/logo.png";
				}								
			})
			console.log("请求模板数据成功……");			
		}else{
			layer.msg('请求数据失败',{icon:6});
		}					
	}else{
		layer.msg('请求数据不存在',{icon:2});
	}
}

/**
 * 获取组件模板
 */
var getComponentTemplate = function(){
	var returnHtml = '';
	$.ajax({ 
		type: "get",
		url: templateUrl, 
		async: false, 
		dataType: "html",
		success: function(html){ 
			if(html){
				returnHtml = html;
				console.log('请求组件模板成功……');				
			}else{
				console.log('请求组件模板失败……');
				layer.msg('请求组件模板失败……', {icon: 2});
			}			
		},
	})
	return returnHtml;
}

/**
 * 获取和设置后端获取的动态数据
 */
var getSetActiveData = function(){
	console.log("列表数据链接dataUrl识别……");
	layer.open({
		type: 2,
		title: '设置列表信息',
		area: ['900px', '600px'],
		btn: ['确定', '取消'],
		content: '/html/htmlCreatePage/listComponentSonCreate.html',
		yes: function(index, sonDom){
			//获取子页面设置的值
			var sonWindow = window[sonDom.find('iframe')[0]['name']];
			var listFirstText = sonWindow.getListFirstText();
			var listSecondText = sonWindow.getListSecondText();  
			var listStatusSetJson = sonWindow.getListStatusSet();
			var listStateText = sonWindow.getListStateText();
			
			//赋值
			console.log('子页面关闭，设置列表前后文本值……');
			if(listFirstText){
				$('input[name=text_first]').val($.trim(listFirstText));
			}
			if(listSecondText){
				$('input[name=text_second]').val($.trim(listSecondText));
			}
			if(listStateText){
				$('input[name=text_stateColor]').val($.trim(listStateText));
			}
			console.log(listStatusSetJson);
			if(listStatusSetJson && listStatusSetJson != {}){
				for(var item in listStatusSetJson){
					$('input[name=' + item + ']').val(listStatusSetJson[item]);
				}
			}
			
			//恢复数据input的状态，关闭子页面
			isChooesdDataUrlInput = false;
			layer.close(index);
			
			//重新更新配置数据
			$('input[name=text_first]').focus();
			$('input[name=text_first]').blur();
		},
		cancel: function(index, sonDom){
			layer.close(index);
		},	
	})
}

/**
 * input框blur事件函数
 */
var inputBlurEvent = function(){
	//提交验证
	$('#text_content_three_save').click();	
}

/**
 * 数据链接Input的click事件绑定
 */
var dataUrlInputClickEvent = function(){
	//数据链接Input处于选中状态
	isChooesdDataUrlInput = true;
	renderData.text = [];	 
}

/**
 * 导出html按钮click事件函数
 */
var buttonClickEvent = function(){		
	generateHTML(getWholeGenerateHtml(), "list_" + getNowTime('-','-') + ".html");
}

/**
 * 保存html按钮click事件函数
 */
var saveButtonClickEvent = function(){		
	ajaxByPost("/upload/string",
				{
					wholePageCode: getWholeGenerateHtml(),
					pathName: 'list'
				},
				saveButtonClickSuccess);
}

var saveButtonClickSuccess = function(jsonData){
	if(jsonData && jsonData != ''){
		if(jsonData.state == 0){
			layer.msg('保存成功' + jsonData.message, {icon: 1});
		}else{
			layer.msg('保存失败：' + jsonData.message, {icon: 4});
		}
	}else {
		layer.msg('请求失败', {icon: 6});
	}	
}

/**
 * 生成的html的代码
 */
var getWholeGenerateHtml = function(){
	var firstPartHtml = '<!DOCTYPE html>' +
			'<html>                       ' +
			'<head>                       ' +
			'	<meta charset="utf-8">    ' +
			'	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">  ' +
			'	<title> 数据表格 </title>  ' +
			'	<link rel="stylesheet" href="/package/layui-2.2.5/css/layui.css">  ' +
			'   <script type="text/javascript" src="/package/layui-2.2.5/layui.all.js"></script> '  + 
			'   <script type="text/javascript" src="/package/jquery-3.3.1/jquery-3.3.1.min.js"></script>     '     +
			'   <script type="text/javascript" src="/javaScript/universal.js"></script>    '   +
			'</head>  <body> ' ;
	var cssJs = '  <script type="text/javascript" >'+ 'var text_content_one=' + 
				JSON.stringify(text_content_one) + 
				'  </script>';  
	var secondPartHtml = '</body> </html> ';
	return firstPartHtml + cssJs + $container.html() + secondPartHtml;
}

/**
 * 重置按钮按钮click事件函数
 */
var resetButtonClickEvent = function(){		
	window.location.reload();
}
