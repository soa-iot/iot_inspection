/**
 * 临时任务下达
 */

//日期组件
layui.use(['layer','laydate'], function(){
	
	var laydate = layui.laydate;
	laydate.render({
	    elem: '#create_start_date' //指定元素
	   ,type: 'date'
	  });
	
	laydate.render({
	    elem: '#create_end_date' //指定元素
	   ,type: 'date'
	  });
	
	laydate.render({
	    elem: '#require_start_date' //指定元素
	   ,type: 'date'
	  });
	
	laydate.render({
	    elem: '#require_end_date' //指定元素
	   ,type: 'date'
	  });
})

var userAccount = getUrlParamValueByName("executePerson");
layui.use(['layer', 'form', 'laydate', 'table'], function(){
	
	var layer = layui.layer
		,laydate = layui.laydate
	    ,form = layui.form
	    ,table = layui.table;
	
	/**
	 * 临时任务表格
	 */
	var taskTable = table.render({
	    elem: '#taskList'
	    ,url: '/iot_inspection/temporarytask/show/list'
	    /*,height: 465*/
	    ,page: true //开启分页
	    ,loading: true
	    ,where: {
	    	'taskState': 'TODO',
	    	'executePerson': (userAccount==null || userAccount==''?'undefined':userAccount)
	    }
	    ,parseData: function(res){ //res 即为原始返回的数据
			var data = res.data;
			
			if(data != null){
				$("#unfinish-num").text(data.unFinishCount);
				$("#overfinish-num").text(data.overFinishCount);
				
				if(data.list != null && data.list.length != 0){
					for(var i=0;i<data.list.length;i++){
						if(data.list[i].taskState === 'UNFINISHED'){
							data.list[i].taskState = '未完成';
						}else if(data.list[i].taskState === 'OVERFINISHED'){
							data.list[i].taskState = '超期未完成';
						}
					}
				}
			}
			
			return {
				"code": res.state,      //解析接口状态
				"msg": res.message,        //解析提示文本
				"count": data.count,   //解析数据长度
				"data": data.list      //解析数据列表
			};
	    }
	    ,cols: [[ //表头
	      {field: 'id', title: '编号', width:'5%', sort:false, type:'numbers', fixed:'left', align:'center'}
	      ,{field:'taskName', title:'任务名称', width:'18%', sort:true, align:'center'}
	      ,{field:'createPerson', title:'下达人', width:'10%', sort:true, align:'center'}
	      ,{field:'departmentName', title:'下达部门', width:'12%', sort:true, align:'center'}
	      ,{field:'createTime', title:'下达日期', width:'11%', sort:true, align:'center'}
	      ,{field:'requireFinishTime', title:'要求完成日期', width:'12%', sort:true, align:'center'}
	      ,{field:'executePerson', title:'执行人', width:'12%', sort:true, align:'center'}
	      /*,{field:'actualFinishTime', title:'任务完成时间', width:'12%', sort:true, align:'center'}*/
	      ,{field:'taskState', title:'任务状态', width:'10%', sort:true, align:'center'}
	      ,{fixed:'right',  title:'操作', minWidth:105, width:'10%', align:'center', toolbar:'#barBtn'} 
	    ]]
	  });
	
	
	/**
	 * 重新加载表
	 */
	function reloadTable(sortField, sortType){
		taskTable.reload({
    	   page: {
    		   curr: 1 //重新从第 1 页开始
    	   }
    	   ,where: {
    		    'createStartDate': $.trim($("#create_start_date").val()),
    		    'createEndDate': $.trim($("#create_end_date").val()),
    		    'requireStartDate':	$.trim($("#require_start_date").val()),
    		    'requireEndDate': $.trim($("#require_end_date").val()),
    		    'taskName': $.trim($("#taskName").val()),
    		    'taskState': ($.trim($("#taskState").val())==''||$.trim($("#taskState").val())==null?'TODO':$.trim($("#taskState").val())),
    		    'createPerson': $.trim($("#createPerson").val()),
    		    'executePerson': userAccount,
    			'orderField': sortField,
    			'orderName': sortType,
    	   }
    	})
	}
	
	/**
	 * 条件查询俺按钮点击事件
	 */
	$("#queryBtn").click(function(){
		console.log("----点击条件查询按钮----");
		
		reloadTable(null, null);
	})
	
	 /**
	  * 监听排序事件
	  */
	 table.on('sort(taskList)', function(obj){
		 console.log(obj.field); //当前排序的字段名
		 console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
		 var field = fieldMapping[obj.field];
		 reloadTable(field, obj.type);
	 });
	
	/**
	 * 监听每一行工具事件
	 */

	table.on('tool(taskList)', function(obj){
		console.log(obj);
	    var data = obj.data;
	    if(obj.event === 'detail'){
	    	location.href = "/iot_inspection/html/temporary_task/temporary_task_handle.html?taskID="+data.taskID+"&executePerson="+userAccount+"&isBack=0";
	    	/*var tab = window.parent.document.getElementById("layui-tab-title");
	    	var content = window.parent.document.getElementById("layui-tab-content");
	    	var href="/iot_inspection/html/temporary_task/temporary_task_handle.html?taskID="+data.taskID;
	    	var title = '<li class="layui-icon">临时任务处理</li>';
	    	var iframe = '<div class="layui-tab-item" style="background: #f5f5f5;"> <iframe src="'+href+'" width="100%" height="100%" name="iframe"'+
				'scrolling="auto" class="iframe" framborder="0"></iframe></div>';
			$(tab).append(title);
			$(content).append(iframe);*/
	    }
	});
})


var fieldMapping = {
	"taskName":"TASK_NAME",
	"opeName":"OPE_NAME",
	"createPerson":"CREATE_PERSON",
	"createTime":"CREATE_TIME",
	"requireFinishTime":"REQUIRE_FINISH_TIME",
	"actualFinishTime":"ACTUAL_FINISH_TIME",
	"departmentName":"DEPARTEMENT_NAME",
	"executeDepartmentName":"EXECUTE_DEPARTEMENT_NAME",
	"executePerson":"EXECUTE_PERSON",
	"taskState":"TASK_STATE"
}

/**
 * 根据浏览器urlf的参数名，获取参数值
 */
function getUrlParamValueByName ( name ) {
    var reg = new RegExp( "(^|&)" + name + "=([^&]*)(&|$)", "i" );
    var r = window.location.search.substr( 1 ).match( reg ); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if ( r != null )
    context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : decodeURI(context);
}
