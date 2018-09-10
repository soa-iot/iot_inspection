/**
 * 刷新页面方法1
 */
function reloadHTML1(){
	//清空
	var tableJsArr = [];
	$.each( $( '.component_container' ), function( index, item ){
		$( item ).find( ':not(script)' ).remove();
		tableJsArr.push( $( item ).html() );
		$( item ).html( '' );
	})
	
	//更新
	$.each( $( '.component_container' ), function( index, item ){
		$( item ).html( tableJsArr[index] ); 
	})
}