
//加载layui内置模块
layui.use(['jquery','form','layer','table'], function(){
	var form = layui.form
	,layer = layui.layer
	,table = layui.table
	,$ = layui.$; //使用jQuery

	
	/**
	 * 设备信息展示表
	 */
	var equipmentTable = table.render({
		elem: '#equipmentInfo',
		method: 'post',
		url: '/iot_inspection/equipment/show',
		/*toolbar: '#toolbarBtn',
		defaultToolbar: [''],*/
		totalRow: true,
		page: true,   //开启分页
		cellMinWidth: 130,
		request: {
		    pageName: 'page' //页码的参数名称，默认：page
		    ,limitName: 'limit' //每页数据量的参数名，默认：limit
		},
		parseData: function(res){ //res 即为原始返回的数据
		    return {
		      "code": res.code, //解析接口状态
		      "msg": res.msg, //解析提示文本
		      "count": res.count, //解析数据长度
		      "data": res.data      //解析数据列表
		    };
		},
		cols: [[
			{type:'radio'},
			{field:'welName', title:'装置列名', align:'center'},    //, templet:"<div>{{layui.util.toDateString(d.applydate,'yyyy-MM-dd HH:mm:ss')}}</div>"
			/*{field:'welUnit', title:'装置单元', align:'center'},*/
			{field:'equMemoOne', title:'设备类别', align:'center'},
			{field:'equPositionNum', title:'设备位号', align:'center'},
			{field:'equName', title:'设备名称', align:'center'}]]
	});
	
	/**
	 * onblur失去焦点事件
	 */
	$(".layui-form-select dd").click(function(){
		tableReload();
	});
	$("#equMemoOne").blur(function(){
		tableReload();
	});
	$("#equPositionNum").blur(function(){
		tableReload();
	});
	$("#equName").blur(function(){
		tableReload();
	});
	
	
	/**
	 * 表重新加载
	 */
	function tableReload(){
		equipmentTable.reload({
    		url: '/iot_inspection/equipment/show'
    	   ,page: {
    		   curr: 1 //重新从第 1 页开始
    	   }
    	   ,where: {
    			'welName': $("#welName").val(),
    			'equMemoOne': $("#equMemoOne").val(),
    			'equPositionNum': $("#equPositionNum").val(),
    			'equName': $("#equName").val(),
    	   }
    	})
	}
	
	
	/**
	 * 监听头工具栏事件 
	 */ 
	/*
	  table.on('toolbar(equipmentInfo)', function(obj){
	     //var checkStatus = table.checkStatus(obj.config.id);
		 console.log(obj);
		 switch(obj.event){
	      case 'querydata':
	    	console.log('querydata');
	    	equipmentTable.reload({
	    		url: '/iot_process/equipment/show'
	    	   ,page: {
	    		   curr: 1 //重新从第 1 页开始
	    	   }
	    	   ,where: {
	    			'welName': $("#welName").val(),
	    			'equMemoOne': $("#equMemoOne").val(),
	    			'equPositionNum': $("#equPositionNum").val(),
	    			'equName': $("#equName").val(),
	    	   }
	    	})
	        break;
	      case 'delete':
	    	  console.log('delete');
	    	  layer.msg("功能正在完善中...",{icon: 5})
	    };
	  });*/
})