var table = layui.table;
var layer = layui.layer;
var templ = layui.laytpl;
var form = layui.form; 

/**
 * 表格对象的属性和行为
 */
function TableObject( renderParam, paramJson ){	
	/*
	 * 参数定义
	 */
	//表格模板地址
	var _tableTemplateUrl = '/javaScript/devbus/table/tableTemplateTest.js';
	//表格模板js地址
	var _tableTemplateJsUrl = '/javaScript/devbus/table/tableTemplateJSTest.js';
	//数据访问参入参数
	var _renderParam =  renderParam;
	//数据访问参入参数
	var _paramJson = paramJson;
	
	/*
	 * 获取表格模板代码
	 */
	var _getTableTemplateCode = function(){
		var returnHtml;
		//获取组件模板
		$.ajax({ 
			type: "get",
			url: _tableTemplateUrl, 
			cache: true, 
			async: false,
			dataType: "html",
			success: function(html){ 
				returnHtml = html;
			},
		})
		return returnHtml;
	}
	
	/*
	 * 获取模板js
	 */
	var _getTableTemplateJsCode = function(){
		var returnJsHtml;
		//获取组件模板js
		$.ajax({ 
			type: "get",
			url: _tableTemplateJsUrl, 
			cache: true, 
			async: false,
			dataType: "html",
			success: function(html){ 
				console.log(html);
				returnJsHtml = html;
			},
		})
		return returnJsHtml;
	}
	
	/*
	 * 接受传入方法参数
	 */
	var _setTableTemplateParam = function(renderParam){
		this._renderParam = renderParam;
	}
	
	/*
	 * 渲染模板，返回HTML
	 */
	var _renderTableTemplate = function(){
		var renderedHtml ;
		var originHtml = _getTableTemplateCode();
		console.assert(originHtml, '获取模板数据错误');
		templ(originHtml).render(renderParam, function(html){
			renderedHtml = html;
		})
		return renderedHtml;
	}

	/*
	 * 获取最終HTML
	 */
	this.getWholeHtml = function(){
		return _renderTableTemplate();
	}
	
	/*
	 * 获取最終js
	 */
	this.getWholeJs = function(){
		return  _getTableTemplateJsCode();
	}
}


