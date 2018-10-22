/**
 * 插件扩展
 */
(function ($) {
	   //1.定义jquery的扩展方法bootstrapSelect
	$.fn.bootstrapSelect = function (options, param) {
		if (typeof options == 'string') {
			return $.fn.bootstrapSelect.methods[options](this, param);
		}
	   //2.将调用时候传过来的参数和default参数合并
	   options = $.extend({}, $.fn.bootstrapSelect.defaults, options || {});
	   //3.添加默认值
	   var target = $(this);
	   if (!target.hasClass("selectpicker")) target.addClass("selectpicker");
	   target.attr('valuefield', options.valueField);
	   target.attr('textfield', options.textField);
	   target.empty();
	   var option = $('<option></option>');
	   option.attr('value', '');
	   option.text(options.placeholder);
	   target.append(option);
	   //4.判断用户传过来的参数列表里面是否包含数据data数据集，如果包含，不用发ajax从后台取，否则否送ajax从后台取数据
	   if (options.data) {
	       init(target, options.data);
	   }
	   else {
	       //var param = {};
	       options.onBeforeLoad.call(target, options.param);
	       if (!options.url) return;
	       $.getJSON(options.url, options.param, function (data) {
	           init(target, data);
	       });
	   }
	   function init(target, data) {
	       $.each(data, function (i, item) {
	           var option = $('<option></option>');
	   option.attr('value', item[options.valueField]);
	           option.text(item[options.textField]);
	           target.append(option);
	       });
	       options.onLoadSuccess.call(target);
	   }
	   target.unbind("change");
	   target.on("change", function (e) {
	           if (options.onChange)
	               return options.onChange(target.val());
	       });
	   }
	
	   //5.如果传过来的是字符串，代表调用方法。
	   $.fn.bootstrapSelect.methods = {
	       getValue: function (jq) {
	           return jq.val();
	       },
	       setValue: function (jq, param) {
	           jq.val(param);
	       },
	       load: function (jq, url) {
	           $.getJSON(url, function (data) {
	               jq.empty();
	               var option = $('<option></option>');
	   option.attr('value', '');
	   option.text('请选择');
	   jq.append(option);
	   $.each(data, function (i, item) {
	       var option = $('<option></option>');
	   option.attr('value', item[jq.attr('valuefield')]);
	   option.text(item[jq.attr('textfield')]);
	                   jq.append(option);
	               });
	           });
	       }
	   };
	
	   //6.默认参数列表
	   $.fn.bootstrapSelect.defaults = {
	       url: null,
	       param: null,
	       data: null,
	       valueField: 'value',
	   textField: 'text',
	   placeholder: '请选择',
	       
	   };
	
	   //初始化
	   $(".selectpicker").each(function () {
	var target = $(this);
	target.attr("title", $.fn.bootstrapSelect.defaults.placeholder);
	    target.selectpicker();
	});
})(jQuery);


/**
 * 参数定义
 */
var formUrl = "http://localhost:8080/processBusiness/problemDealInfo/problemReport";
	


/**
 * 页面初始化
 */
$(function(){
	console.log( '问题上报页面初始化……' );	
	/*
	 * 初始化文件上传控件
	 */
	$( '#fileUploadProblemReport' ).fileinput({
        language: 'zh', //设置语言
        zoomModalHeight: 100,
        uploadUrl: "", //上传的地址
        uploadAsync: true, //异步上传
        //uploadExtraData： {}, //上传数据
        allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
        showUpload: true, //是否显示上传按钮
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式             
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
    });
	
	/*
	 * 给上传文件控件、下拉多选框添加name属性，满足validate要求
	 */
	$( '#pictureProblemReport' ).find( 'input' ).attr( 'name' , 'picture');
	$( 'input[aria-label=Search' ).attr( 'name' , 'igore');
	/*
	 * 初始化下拉多选框控件
	
	$('#sel').bootstrapSelect({
	    url:'',
	    data: {},
	    valueField: 'value',
	    textField: 'text',
	}); */
	
	/*
	 * 初始化姓名
	 */
	var userNameTemp = getCookie1( 'userName' ) ,
		userName = userNameTemp.substr( 1, userNameTemp.length - 2 ),
		userOrganizationTemp = getCookie1( 'userOrganization' ) ,
		userOrganization = userOrganizationTemp.substr( 1, userOrganizationTemp.length - 2 ),
		userRoleTemp = getCookie1( 'userRole' ) ,
		userRole = userRoleTemp.substr( 1, userRoleTemp.length - 2 );
	$( 'input[name=reporter]' ).val( userName );
})


/**
 * 事件绑定
 */
$(function(){	
	/*
	 * 参数验证绑定
	 */
	$( '#formProblemReport' ).validate({
		rules : {
			reporter : {
				required : true,
				minlength : 1
			},
			area : {
				required : true
			},
			positionUnit : {
				required : true
			},
			profession : {
				required : true
			},
			problemType : {
				required : true
			},
			unsafetyAction : {
				required : true
			},
			detailAction : {
				required : true
			},
			equipmentPositionNum : {
				required : true
			},
			problemDescribe : {
				required : true
			},
		},
		messages : {
			reporter : {
				required : "上报人名不能为空"
			},
			area : {
				required : "问题区域不能为空"
			},
			positionUnit : {
				required : "属地单位不能为空"
			},
			profession : {
				required : "所属专业不能为空"
			},
			problemType : {
				required : "问题类型不能为空"
			},
			unsafetyAction : {
				required : "不安全行为状态不能为空"
			},
			detailAction : {
				required : "具体行为不能为空"
			},
			equipmentPositionNum : {
				required : "问题定位不能为空"
			},
			problemDescribe : {
				required : "问题描述不能为空"
			},
		},
		onfocusout :  function( element ){
			$( element ).valid();
		},
		onsubmit : true,
		debug: true,
		focusInvalid : true,
		highlight : function( element ) {
	    },
	    success : function( label ) {
        },
        submitHandler : function( form ){       	
        },
        errorPlacement : function ( error, element ) { //指定错误信息位置
            if ( element.is( ':radio' ) || element.is( ':checkbox' ) 
            		|| element.is( '.selectpicker' ) ) { //如果是radio或checkbox
                var eid = element.attr( 'name' ); //获取元素的name属性
                error.appendTo( element.parent() ); //将错误信息添加当前元素的父结点后面
            } else {
                error.insertAfter( element );
            }
        }   
	})
	
	/*
	 * 提交按钮单击事件
	 */
	$( '#submitProblemReport' ).on( 'click', function(){
		//提交前验证
		if( !$( '#formProblemReport' ).valid() ){
			console.log( '提交前验证问题上报表单未通过' );
			return;
		}
    	$.ajax({
	   	     type: "POST",
	   	     url: formUrl,
	   	     data: $( '#formProblemReport' ).serialize(),
	   	     async: true, //默认
	   	     cache: false, //默认
	   	     contentType: "application/json",//默认
	   	     dataType: "json",//必须指定，否则根据后端判断
	   	     beforeSend:  function(XMLHttpRequest){//在beforeSend中如果返回false可以取消本次ajax请求
	   	         //this;   //调用本次ajax请求时传递的options参数
	   	     },
	   	     complete:   function(XMLHttpRequest, textStatus){//请求完成后调用的回调函数（请求成功或失败时均调用）
	   	        //this;    //调用本次ajax请求时传递的options参数
	   	     },
	   	     success: problemReportSucessFunction,
	   	     error:function(){
	   	    	 layer.msg('请求失败');
	   	     }		       
	   	});
		return false;
	} )
	
	/*
	 * 重置按钮单击事件
	 */
	
	/*
	 * 取消按钮单击事件
	 */
})

/**
 *  参数验证
 */
function checkFormInfoAndSubmit(){
	console.log( '验证和提交问题上报表单……' );
	
}

/**
 * 表单提交成功回调函数
 */
function problemReportSucessFunction( jsonData ){
	console.log( '表单提交成功回调函数……' );
	if( jsonData != null ){
		if( jsonData.data != null && jsonData.state != 1 ){
			console.log( "问题上报成功……" );			
			/*
			 * 不跳转页面，刷新赋值当前页面
			 */
			
			/*
			 * ajax请求上传图片
			 */
			
		}else{
//			BootstrapDialog.show({
//				title : "提示信息",
//				message : "请求数据为空",
//				animate : true,
//				buttons : [{
//	                label : '确定',
//	                action : function( dialogRef ){
//	                    dialogRef.close();
//	                }
//	            }]
//			})
		}
	}else{
//		BootstrapDialog.show({
//			title : "提示信息",
//			message : "访问失败",
//			animate : true,
//			buttons : [{
//                label : '确定',
//                action : function( dialogRef ){
//                    dialogRef.close();
//                }
//            }]
//		})
	}	
}
