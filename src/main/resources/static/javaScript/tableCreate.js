/*
 * 初始化参数
 */
var global = {};//全局变量
var table = layui.table;
var layer = layui.layer;
var templ = layui.laytpl;
var form = layui.form;
var tableDataUrl = ''; //表格数据链接
var isChoosed = false; //表格的数据链接input框是否被选中
var $container;
var templateUrl = "/javaScript/templateJs/tableComponent.js";//模板地址
var text_content_one = {                     //组件配置数据			
	"tableID": "",
	"tableDataUrl": "/html/analogData/tableJsonTest2.html",
	"tableLeft": "0px",
	"tableRight": "0px",
	"tableTop": "0px",
	"isShowPage": false,
	
	"tableRowHeight": "",	
	
	"isShowTitle": true,	
	"tableTitle": "设备综合指标",
	"tableTitleHeight": "8%",
	"tableTitleTop": "12px",	
	"tableTitleBottom": "16px",	
	"tableTitleBgcolor": "#001065", 
	
	"tableTitlePart1Width": "60%",
	"tableTitlePart1Left": "20px",
	"tableTitlePart1Border": "2px #02448C solid",
	"tableTitlePart1Color": "#A832CD", 
	"tableTitlePart1Size": "22px",
	"tableTitlePart1Family": "宋体",	

	"tableTitleMore": "more",
	"tableTitleMoreHeight": "100%",
	"tableTitleMoreColor": "#A832CD", 
	"tableTitleMoreSize": "15px",
	"tableTitleMoreFamily": "宋体",
	
	"tableTitleCondition": ['年', '月', '日'],
	"tableTitlePart2Width": "40%",
	"tableTitlePart2Right": "20px",
	"tableTitlePart2Border": "2px #02448C solid",
	"tableTitlePart2Color": "white", 
	"tableTitlePart2Size": "14px",
	"tableTitlePart2Family": "宋体",	
	
	"tableHeadFontSize": "14px",
	"tableHeadFontFamily": "华文仿宋",
	"tableHeadFontColor": "#60D4F5",
	"tableHeadBackgroundColor": "#0043FE",
	"tableHeadField": ["equipment_name", "equipment_org","equipment_total_efficient","equipment_time_efficient","equipment_open_efficient","equipment_good_efficient","equipment_total_energy", "equipment_mttr_time"],
	"tableHeadTitle": ["设备名称", "所属组织", "OEE综合效率", "时间开动率", "性能开动率", "良品率", "累计能耗", "MTTR"],
	"tableHeadWidth": ["120", "80", "80", "80", "80", "80", "80", "80"], 
	"tableHeadFixed": ["", "", "", "", "", "", "", ""],
	"tableHeadAlign": ["center", "center", "center", "center", "center", "center", "center", "center"],	
	
	"tableBodyFontSize": "10px",
	"tableBodyFontFamily": "华文仿宋",
	"tableBodyFontColor": "#FFFFFF",
	"tableBodyEvenBackgroundColor": "#04186F",
	"tableBodyOddBackgroundColor": "#00104E",
	
	"tableClosContentType": [ "0", "0", "0", "0", "0", "0", "0", "0" ],	
	"tableClosContentJudge": [ 
		{"name": "project_name", "value": []}, 
		{"name": "project_begin_date", "value": []}, 
		{"name": "project_end_date", "value": []},
		{"name": "project_event_type", "value": ["告警事件","故障事件","消息事件"]},
		{"name": "project_state", "value": ["发布","维护","更新","正常"]} 
	],	
	"tableClosContentColor": [ 
		{"name": "project_name", "value": []}, 
		{"name": "project_begin_date", "value": []}, 
		{"name": "project_end_date", "value": []},
		{"name": "project_event_type", "value": ["#D1960B", "#EA2D15", "#07A22A"]},
		{"name": "project_state", "value": ["#C28914", "#048C33", "#D92B15", "#01E793"]} 
	]
}
global.text_content_one = text_content_one;

/**
 * html页面初始化
 */
$(function(){	
	$container = $('#text_content_one');//组件容器对象

	//获取组件模板\
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
});

/**
 * 事件绑定
 */
$(function(){	 
	//所有输入框blur事件绑定
	$('input[type=text]').on('blur', inputBlurEvent);
	
	//导出html按钮单击事件绑定
	$('#text_content_two_button button').on('click', buttonClickEvent);
	
	//导出html按钮单击事件绑定
	$('#text_content_three_button button').on('click', saveButtonClickEvent);
	
	//表单提交验证事件绑定
	layui.form.on('submit(save)', function(data){	
		console.log("表单blur事件触发");
		$.each($('input[type=text]'), function(index, item){
			var itenValue =  $(item).val();
			
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
			
			//判断是否分页并赋值
			if($(item).attr('name') == 'isShowPage'){
				console.log("表格分页数据识别……");
				if(itenValue == 'false'){
					text_content_one.isShowPage = false;
				}else{
					text_content_one.isShowPage = true;
				}	
				return true;
			}	
			
			//判断是否显示标题行
			if($(item).attr('name') == 'isShowTitle'){
				console.log("表格标题是否显示识别……");
				if(itenValue == 'false'){
					text_content_one.isShowTitle = false;
					console.log("1111111111");
				}else{
					text_content_one.isShowTitle = true;
					console.log("222222222222");
				}	
				return true;
			}
			
			//判断  条件内容  ？ ，并赋值
			if($(item).attr('name') == 'tableTitleCondition'){
				console.log("表格标题行条件识别，并赋值……");
				var tableTitleCondition = new Array();
				if(contains(itenValue, ',')){
					tableTitleCondition = itenValue.split(",");
				}else if(contains(itenValue, '.')){
					tableTitleCondition = itenValue.split(".");
				}else{
					tableTitleCondition = itenValue.split("，");
				}
				text_content_one[$(item).attr('name')] = tableTitleCondition;
				return true;
			}
			
			//判断表格表头列宽度并赋值
			if($(item).attr('name') == 'tableHeadWidth' ){
				console.log("表格表头列宽度识别……");
				var tableHeadWidthContent = new Array();
				if(contains(itenValue, ',')){
					tableHeadWidthContent = itenValue.split(",");
				}else if(contains(itenValue, '.')){
					tableHeadWidthContent = itenValue.split(".");
				}else{
					tableHeadWidthContent = itenValue.split("，");
				}
				text_content_one[$(item).attr('name')] = tableHeadWidthContent;
				return true;
			}
			
			//判断表格表头字段并赋值
			if($(item).attr('name') == 'tableHeadField' ){
				console.log("表格字段数据识别……");
				var tableHeadFieldContent = new Array();
				if(contains(itenValue, ',')){
					tableHeadFieldContent = itenValue.split(",");
				}else if(contains(itenValue, '.')){
					tableHeadFieldContent = itenValue.split(".");
				}else{
					tableHeadFieldContent = itenValue.split("，");
				}
				text_content_one[$(item).attr('name')] = tableHeadFieldContent;
				return true;
			}
			
			
			//判断表头名称并赋值
			if($(item).attr('name') == 'tableHeadTitle' ){
				console.log("表格名称数据识别……");
				var tableHeadContent = new Array(); //表头名称分组
				$('#checkboxStyle').empty();				
				if(contains(itenValue, ',')){
					tableHeadContent = itenValue.split(",");
				}else if(contains(itenValue, '.')){
					tableHeadContent = itenValue.split(".");
				}else{
					tableHeadContent = itenValue.split("，");
				}	
				text_content_one[$(item).attr('name')] = tableHeadContent;
				
				//赋值多选框
				$.each(tableHeadContent, function(index, item){
					$('#checkboxStyle').append(
							' <input type="checkbox" lay-filter="tableHeadName" name="' + item + '" title="' + item + '"> '
					);
				})
				form.render();	
				
				//多选框单击事件绑定
				form.on('checkbox(tableHeadName)', function(data){
					console.log("执行多选框单击事件……");
					var nowName = data.elem.name;
					if(data.elem.checked){					
						//显示选中项的设置项
						$(this).closest('.layui-tab-item').append(
								' <div class="layui-form-item" name="' + nowName + '"> ' +
								' 	<label class="layui-form-label">列样式设置:</label> ' +
								' 	<div class="layui-input-block" > ' +
								' 		<input type="radio" name="tableClosContentType" lay-filter="colStyle" value="1" title="样式1"> ' +
								' 		<input type="radio" name="tableClosContentType" lay-filter="colStyle" value="2" title="样式2"> ' +
								' 	</div> ' +
								' </div> '
						).append(	
								' <div class="layui-form-item" name="' + nowName + '">  ' + 
								' 	<label class="layui-form-label">对比值:</label>  ' +
								' 	<div class="layui-input-inline" style="width:280px">	  ' +
								'   	<input type="text" name="tableClosContentJudge" required lay-verify="" placeholder="" ' +
								'   		autocomplete="off" class="layui-input" value="">' +
								'  	</div> ' +
								' </div>  '
						).append(
								' <div class="layui-form-item" name="' + nowName + '">  ' + 
								' 	<label class="layui-form-label">对应颜色:</label>  ' +
								' 	<div class="layui-input-inline" style="width:280px">  ' +
								'   	<input type="text" name="tableClosContentColor" required lay-verify="" placeholder="" ' +
								'   		autocomplete="off" class="layui-input" value="">' +
								'  	</div> ' +
								' </div>  '
						);
						form.render();
						
						//样式单选按钮事件绑定
						form.on('radio(colStyle)', function(data){
							console.log("执行单选按钮单击事件……");
						});
						form.render();
					}else{
						console.log("nowName:" + nowName);
						console.log($(data.elem).closest('.layui-form-item').siblings('div'));
						var $sameSonElem = $(data.elem).closest('.layui-form-item').siblings('div').filter(function(){
							console.log(this);
							console.log($(this).attr('name'));
							console.log($(this).attr('name') == nowName);
							return $(this).attr('name') == nowName;
						});
						$sameSonElem.remove();
					}					
				});
				return true;
			}	
			
			//判断表格字段名称赋值(通用)			
			var tempType = $(item).attr('name');			
			text_content_one[tempType] = itenValue;	
			
			//展示更新数据链接
			if(isChoosed && $(item).attr('name') == 'tableDataUrl'){
				console.log("表格数据链接tableDataUrl识别……"); 
				tableDataUrl = itenValue;
				layer.open({
					type: 2,
					title: '选择列字段和表头名称',
					btn: ['确定', '取消'],
					area: ['700px','400px'],
					content: '/html/htmlCreatePage/tableComponentSonCreate.html',
					//此处要求返回的格式
					success: function(){
						
					},
					yes: function(index, sonDom){
						var sonWindow = window[sonDom.find('iframe')[0]['name']];
						var tempNameStr = sonWindow.getTableHeadName();
						var tempFieldStr = sonWindow.getTableHeadField();  
						
						//赋值
						if(tempNameStr && $.trim(tempNameStr)){ 
							$('input[name=tableHeadTitle]').val(tempNameStr);
						}
						if(tempFieldStr && $.trim(tempFieldStr)){ 
							$('input[name=tableHeadField]').val(tempFieldStr);
						}						
						layer.close(index);
						
						//判断表格字段名称赋值						
						text_content_one.tableDataUrl = itenValue;	
						isChoosed = false;
						
						//失去焦点，更新数据
						$('input[name=tableHeadTitle]').focus();
						$('input[name=tableHeadTitle]').blur();
					},
					cancel: function(index, layero){
						layer.close(index);
					},
				});
				
			}		
		})														
		console.log(text_content_one);
		console.log("更新数据完成……");
		
		//获取组件模板\
		$.ajax({ 
			type: "get",
			url: templateUrl, 
			//cache: false, //默认 
			dataType: "html",//必须指定，否则根据后端判断 
			success: function(html){ 
				$container.html('');
				htmlRender(html);
			},
		})
		console.log("数据更新表格完成……");
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	//重置按钮事件绑定
	$('button[type=reset]').on('click', resetButtonClickEvent);
});

/**
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

/**
 * input框blur事件函数
 */
var inputBlurEvent = function(){
	//提交验证
	if($(this).attr('name') == 'tableDataUrl'){
		isChoosed = true;
	}else {
		isChoosed = false;
	}
	
	$('#text_content_three_save').click();	
}

/**
 * 导出html按钮click事件函数
 */
var buttonClickEvent = function(){		
	generateHTML(getWholeGenerateHtml(), "table_" + getNowTime('-','-') + ".html");
}

/**
 * 保存html按钮click事件函数
 */
var saveButtonClickEvent = function(){		
	ajaxByPost("/upload/string",
				{
					wholePageCode: getWholeGenerateHtml(),
					pathName: 'table'
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

/***
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