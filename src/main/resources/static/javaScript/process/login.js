/**
 * 参数初始化
 */


/**
 * 页面初始化
 */
$( function(){
	/*
	 * 验证
	 */
	$( '#form' ).validate({
		rules : {
			userName : {
				required : true,
				minlength: 2
			},
			userPassword : {
				required : true
			}
		},
		messages : {
			userName : {
				required : "用户名不能为空",
				minlength: "长度不能小于2"
			},
			userPassword : {
				required : "密码不能为空"
			}
		},
		onfocusout :  function( element ){
			$(element).valid();
		},
		onsubmit : false,
		focusInvalid : true,	
		/*
		highlight : function( element ) {
			$(element).closest('.form-group').addClass('has-error');
	    },
	    success : function(label) {
	    	label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement : function(error, element) {
            element.parent('div').append(error);
        },      
        submitHandler : function( form ) {
        	//form.submit();      	
        } */
	})
})

/**
 * 事件绑定
 */
$( function(){
	/*
	 * 姓名失去光标事件
	 */
	//$( 'input[name=userName]' ).on( 'blur', nameBlurFunction );
	
	/*
	 * 密码失去光标事件
	 */
	//$( 'input[name=userPassword]' ).on( 'blur', passwordBlurFunction );
	
	/*
	 * 登录按钮单击事件
	 */
	$( 'input[type=button]').on( 'click', loginSuccessFucntion );
})


/**
 * 姓名失去光标事件回调函数
 */
function nameBlurFunction(){
	console.log( '姓名input失去光标事件……');
}

/**
 * 密码失去光标事件回调函数
 */
function passwordBlurFunction(){
	console.log( '密码input失去光标事件……');	
}

/**
 * 登录按钮单击事件回调函数
 */
function loginSuccessFucntion(){
	console.log( '提交按钮单击事件……');
	/*
	 * 获取form中input值
	 */
	var userName = $( 'input[name=userName]' ).val();
	var userPassword = $( 'input[name=userPassword]' ).val();
	
	/*
	 * ajax提交
	 */
	var ajaxUrl = "/process/employee/" + userName + "/" + userPassword;
	ajaxByGet( ajaxUrl, {},  submitSuccessFunction, false );
}

/**
 * ajax提交成功回调函数
 */
function submitSuccessFunction( jsonData ){
	if( jsonData != null ){
		if( jsonData.data != null && jsonData.state != 1 ){
			console.log( "请求成功……" );
			//跳转页面
			location.href = 
				"http://localhost:8080/html/process/index.html"
			window.event.returnValue = false;
		}else{
			BootstrapDialog.show({
				title : "提示信息",
				message : "请求数据为空",
				animate : true,
				buttons : [{
	                label : '确定',
	                action : function( dialogRef ){
	                    dialogRef.close();
	                }
	            }]
			})
		}
	}else{
		BootstrapDialog.show({
			title : "提示信息",
			message : "访问失败",
			animate : true,
			buttons : [{
                label : '确定',
                action : function( dialogRef ){
                    dialogRef.close();
                }
            }]
		})
	}	
	
}
