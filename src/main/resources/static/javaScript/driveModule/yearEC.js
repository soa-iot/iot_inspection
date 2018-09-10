/**
 * 全局参数定义
 */
var timeClass = '.dm_time_condition';



/**
 * 页面初始化
 */
$( function(){
		
})

/**
 * 事件绑定
 */
$( function(){
	/*
	 * 时间条件绑定mouseover事件
	 */	
	$( timeClass ).on( "mouseover mouseout" , timeObjMOEvent );	
	
	/*
	 * 时间条件绑定mouseover事件
	 */	
	$( timeClass ).on( "click" , timeObjCLEvent );	
})

/**
 * 时间条件mouseover事件回调函数
 */
function timeObjMOEvent(event){		
	if( event.type == 'mouseover' ){
		console.log( '时间控件的鼠标移入事件……' );
		var tempText = [ 
			"上&nbsp&nbsp&nbsp一&nbsp&nbsp&nbsp年" , 
			"本&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp度" , 
			"下&nbsp&nbsp&nbsp一&nbsp&nbsp&nbsp年" 
		];
		$.each( $( timeClass ) , function( index , item ){
			if( this === item ){
				$( this ).html( tempText[ index ] );
			}
		})
	}else if( event.type == 'mouseout' ){
		console.log( '时间控件的鼠标移出事件……' );
		$.each( $( timeClass ) , function( index , item ){
			$( this ).html( '' );

		})
	}	
}

/**
 * 时间条件mouseover事件回调函数
 */
function timeObjCLEvent(event){		
	console.log( '时间控件的鼠标单击事件……' )
}