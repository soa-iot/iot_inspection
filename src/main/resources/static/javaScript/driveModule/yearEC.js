/**
 * 全局参数定义
 */
var timeClass = '.dm_time_condition',
	paramJson = { 
		time : new Date().getFullYear() 
	},
	currentChooseYear = new Date().getFullYear();



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
		//console.log( '时间控件的鼠标移入事件……' );
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
		//console.log( '时间控件的鼠标移出事件……' );
		$.each( $( timeClass ) , function( index , item ){
			$( this ).html( '' );
		})
	}	
}

/**
 * 时间条件mouseover事件回调函数
 */
function timeObjCLEvent( event ){		
	console.log( '时间控件的鼠标单击事件……' );
	var currentState = $( this ).attr( 'time' );
	/*
	 * 修改当前时间（全局变量）
	 */
	console.log( $( this ).attr( 'time' ) );	
	if( currentState == 'before' ){
		console.log( parseInt( paramJson.time )-- );
		paramJson.time = (-- parseInt( paramJson.time ) ) + '';
	} else if( currentState == 'current' ) {
		paramJson.time = new Date().getFullYear();
	} else if( currentState == 'after' ) {
		console.log( parseInt( paramJson.time ) ++ );
		paramJson.time = ( ++ parseInt( paramJson.time ) ) + '';
	}
	console.log( paramJson.time );	
	
	/*
	 * 刷新页面
	 */
	
}