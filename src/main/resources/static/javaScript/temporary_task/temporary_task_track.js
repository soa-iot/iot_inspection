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
	    elem: '#finish_start_date' //指定元素
	   ,type: 'date'
	  });
	
	laydate.render({
	    elem: '#finish_end_date' //指定元素
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

var userAccount = getCookie1("name");

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
	    ,parseData: function(res){ //res 即为原始返回的数据
			var data = res.data;
			
			if(data != null){
				$("#finish-num").text(data.finishCount);
				$("#unfinish-num").text(data.unFinishCount);
				$("#overfinish-num").text(data.overFinishCount);
				
				if(data.list != null && data.list.length != 0){
					for(var i=0;i<data.list.length;i++){
						if(data.list[i].taskState === 'FINISHED'){
							data.list[i].taskState = '已完成';
						}else if(data.list[i].taskState === 'UNFINISHED'){
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
	      ,{field:'taskName', title:'任务名称', width:'12%', sort:true, align:'center'}
	      ,{field:'createPerson', title:'下达人', width:'9%', sort:true, align:'center'}
	      ,{field:'departmentName', title:'下达部门', width:'12%', sort:true, align:'center'}
	      ,{field:'createTime', title:'下达日期', width:'10%', sort:true, align:'center'}
	      ,{field:'requireFinishTime', title:'要求完成日期', width:'12%', sort:true, align:'center'}
	      ,{field:'executePerson', title:'执行人', width:'9%', sort:true, align:'center'}
	      ,{field:'actualFinishTime', title:'任务完成时间', width:'12%', sort:true, align:'center'}
	      ,{field:'taskState', title:'任务状态', width:'9%', sort:true, align:'center'}
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
    		    'finishStartDate': $.trim($("#finish_start_date").val()),
    		    'finishEndDate': $.trim($("#finish_end_date").val()),
    		    'taskName': $.trim($("#taskName").val()),
    		    'taskState': $.trim($("#taskState").val()),
    		    'createPerson': $.trim($("#createPerson").val()),
    		    'executePerson': $.trim($("#executePerson").val()),
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
	var fileListCreate = $("#fileList-create");
	var fileListFinish = $("#fileList-finish");
	table.on('tool(taskList)', function(obj){
		console.log(obj);
	    var data = obj.data;
	    if(obj.event === 'detail'){
	      layer.open({
	    	title: '临时任务详情信息',
	    	type: 1,
	    	id: obj.event+1,
	    	btnAlign: 'c',
	    	btn: ['关闭'],
	    	closeBtn: 0,
	    	offset: '30px',
	    	area: ['1000px','600px'],
	        content: $("#task-detail"),
	        yes: function(index, layero){
	        	fileListCreate.empty();
	        	fileListFinish.empty();
	            layer.close(index); //如果设定了yes回调，需进行手工关闭
	        },
	        success: function(layero, index){
	        	
	        	$("#layui-row-equip").css("display", "none");
	        	$("#layui-row-describe").css("display", "none");
	        	
	        	$("#createPerson_").val(data.createPerson);
	        	$("#departmentName_").val(data.departmentName);
	        	$("#createTime_").val(data.createTime);
	        	if(data.taskType == 0){
	        		$("#taskType_").val('单人任务');
	        	}
	        	if(data.taskType == 1){
	        		$("#taskType_").val('多人任务');
	        	}
	        	if(data.equNumber.match("其它") == null){
	        		$("#layui-row-equip").css("display", "block");
	        		$("#equNumber_").val(data.equNumber);
		        	$("#equName_").val(data.equName);
	        	}
	        	$("#require_date_").val(data.requireFinishTime);
	        	$("#executePerson_").val(data.executePerson);
	        	$("#taskState_").val(data.taskState);
	        	$("#actualFinishTime_").val(data.actualFinishTime);
	        	$("#taskName_").val(data.taskName);
	        	$("#taskContent_").val(data.taskContent);
	        	$("#riskWarning_").val(data.riskWarning);
	        	if(data.taskState == '已完成'){
	        		$("#layui-row-describe").css("display", "block");
	        		$("#resultDescribe_").val(data.resultDescribe);
	        	}
	        	
	        	//异步请求文件列表
	        	$("#create-task-file").css("display", "none");
	        	$("#finish-task-file").css("display", "none");
	        	$.ajax({
	        		type: "GET",
	        		url: "/iot_inspection/temporarytask/file/showlist",
	        		data: { 
	        			"taskID": data.taskID
	        		},
	        		dataType: "json",
	        		success: function(json){
	        			if(json.state == 0){
	        				if(json.data != null && json.data.length != 0){
	        					for(var i=0;i<json.data.length;i++){
	        						if(json.data[i].fileClass == 0){
	        							$("#create-task-file").css("display", "block");
	        							var tr = $(['<tr>'
	  	  	  	        		          ,'<td>'+ json.data[i].fileOriginalName +'</td>'
	  	  	  	        		          ,'<td>'+ json.data[i].uploadPerson +'</td>'
	  	  	  	        		          ,'<td>'
	  	  	  	        		            ,'<a href="'+'/iot_inspection/temporarytask/file/download/?fileName='+json.data[i].fileOriginalName+'&filePath='+json.data[i].filePath+'"><button type="button" class="layui-btn layui-btn-xs">下载</button></a>'
	  	  	  	        		          ,'</td>'
	  	  	  	        		          ,'</tr>'].join(''));
	  	        						fileListCreate.append(tr);
		        					}
	        						if(json.data[i].fileClass == 1){
	        							$("#finish-task-file").css("display", "block");
	        							var tr = $(['<tr>'
	  	  	  	        		          ,'<td>'+ json.data[i].fileOriginalName +'</td>'
	  	  	  	        		          ,'<td>'+ json.data[i].uploadPerson +'</td>'
	  	  	  	        		          ,'<td>'
	  	  	  	        		            ,'<a href="'+'/iot_inspection/temporarytask/file/download/?fileName='+json.data[i].fileOriginalName+'&filePath='+json.data[i].filePath+'"><button type="button" class="layui-btn layui-btn-xs">下载</button></a>'
	  	  	  	        		          ,'</td>'
	  	  	  	        		          ,'</tr>'].join(''));
	  	        						fileListFinish.append(tr);
		        					}
	        					}
	        				}
	        			}else{
	        				layer.msg("获取临时任务文件失败",{icon: 2, offset:'100px'});
	        			}
	        		},
	        		error: function(){
	        			layer.msg("连接服务器失败",{icon: 2, offset:'100px'});
	        		}
	        	})
	        }
	      });
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