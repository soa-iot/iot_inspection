/**
 *  临时任务下达
 */

//获取当前时间
function getCurrentTime(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	month = month.toString().length==1?"0"+month:month;
	var day = date.getDate();
	day = day.toString().length==1?"0"+day:day;
	var hour = date.getHours();
	hour = hour.toString().length==1?"0"+hour:hour;
	var minute = date.getMinutes();
	minute = minute.toString().length==1?"0"+minute:minute;
	var second = date.getSeconds();
	second = second.toString().length==1?"0"+second:second;
	var curr = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	$("#createTime").val(curr);
}

window.setInterval(getCurrentTime, 1000);

//日期组件
layui.use(['layer','laydate'], function(){
	
	var laydate = layui.laydate;
	laydate.render({
	    elem: '#require_date' //指定元素
	   ,type: 'date'
	   ,min: 0
	  });
})

layui.use(['layer', 'form', 'laydate', 'upload', 'tree'], function(){
	
	var layer = layui.layer
		,laydate = layui.laydate
	    ,form = layui.form
	    ,upload = layui.upload
	    ,tree = layui.tree;
	
	var fileList = $("#fileList"), fileObject={}; 
	var uploadListIns = upload.render({
	    elem: '#fileupload'
	    ,url: '/upload/'
	    ,accept: 'file'
	    ,multiple: true
	    ,auto: false
	    ,bindAction: '#testListAction'
	    ,choose: function(obj){   
	      var files = fileObject = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	      //读取本地文件
	      obj.preview(function(index, file, result){
	    	
	    	console.log(files);
	    	$("#fileTable").css("display", "block");

	        var tr = $(['<tr id="upload-'+ index +'">'
	          ,'<td>'+ file.name +'</td>'
	          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
	          ,'<td>'
	            ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
	          ,'</td>'
	        ,'</tr>'].join(''));

	        //删除
	        tr.find('.demo-delete').on('click', function(){
	          delete files[index]; //删除对应的文件
	          tr.remove();
	          if(fileList.children().length == 0){
	        	  $("#fileTable").css("display", "none");
	          }
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        fileList.append(tr);
	      });
	    }
	  });
	
	$("#userBtn").click(function(){
		layer.open({
		type: 1
		,offset: 't'
		,area: ['300px','400px;']
		,id: 'userTree'+1 //防止重复弹出
		,content: $("#userTree")
		,btn: ['确认',"取消"]
		,btnAlign: 'c' //按钮居中
		,yes: function(index, layero){
			console.log("---------:"+assignUsers);
			//确认按钮的回调函数
			if(assignUsers.length < 1){
				layer.msg("至少选择一名人员", {icon:7, offset: '100px'});
				return;
			}
			$("#executePerson").val(assignUsers);
			
			layer.close(index);
	    }
		,success:function(){	
		}
	});
	})
	
	
	/**
	 * 任务下达按钮点击事件
	 */
	$("#saveBtn").click(function(){
		if($.trim($("#createPerson").val()) == ''){
			  layer.msg("下达人不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		if($.trim($("#taskType").val()) == ''){
			  layer.msg("任务类型不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		if($.trim($("#executePerson").val()) == ''){
			  layer.msg("执行人员不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		if($.trim($("#require_date").val()) == ''){
			  layer.msg("要求完成日期不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		if($.trim($("#equNumber").val()) == ''){
			  layer.msg("设备位号不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		if($.trim($("#taskName").val()) == ''){
			  layer.msg("任务名称不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		if($.trim($("#taskContent").val()) == ''){
			  layer.msg("任务内容不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		
		//ajax异步保存临时任务
		var formData = new FormData();
		formData.append("createPerson", $.trim($("#createPerson").val()));
		formData.append("taskType", $.trim($("#taskType").val()));
		formData.append("executePerson", $.trim($("#executePerson").val()));
		formData.append("requireFinishTime", $.trim($("#require_date").val()));
		formData.append("equNumber", $.trim($("#equNumber").val()));
		formData.append("equName", equName);
		formData.append("taskName", $.trim($("#taskName").val()));
		formData.append("taskContent", $.trim($("#taskContent").val()));
		formData.append("createTime", $.trim($("#createTime").val()));
		formData.append("riskWarning", $.trim($("#riskWarning").val()));
		//遍历上传的文件
		for(var index in fileObject){
			formData.append(index, fileObject[index]);
		}
		
		layer.confirm("是否确定任务下达?", function(){
			$.ajax({
	            url: "/iot_inspection/temporarytask/task/create",
	            data: formData,
	            type: "POST",
	            dataType: "json",
	            processData: false,//用于对data参数进行序列化处理 这里必须false
	            contentType: false, //必须
	            success: function (json) {
	               if(json.state == 0){
	            	   layer.msg("任务下达成功",{icon: 1, offset:'100px'}); 
	            	   $("#executePerson").val("");
	            	   $("#require_date").val("");
	            	   $("#equNumber").val("");
	            	   $("#taskName").val("");
	            	   equName = null;
	            	   $("#taskContent").val("");
	            	   $("#createTime").val("");
	            	   $("#riskWarning").val("");
	            	   for(var index in fileObject){
	            		 delete fileObject[index];
	            	   }
	            	   $("#fileTable").empty();
	           		   $("#fileTable").css("display", "none");
	               }else{
	            	   layer.msg("任务下达失败",{icon: 2, offset:'100px'}); 
	               }
	            },
	            error: function(){
	            	layer.msg("连接服务器失败",{icon: 2, offset:'100px'});
	            }
	        })
		})

	})
	
	
    /**
	 * 定位设备弹窗
	 */
	var equName;
	$("#rfid-btn").click(function(){
		  console.log("rfid");
		  layer.open({
		    	title: '设备定位',
		    	type: 2,
		    	id: 'equipmentInfo',
		    	btn: ['确&nbsp;&nbsp;认','取&nbsp;&nbsp;消','其它设备'],
		    	offset: ['45px','50px'],
		    	area: ['88%','82%'],
		        content: './equipment-location.html',
		        yes: function(index, layero){
		        	//获取iframe窗口的body对象
		        	var body = layer.getChildFrame('body', index);
		        	//找到body对象下被选中的设备位号值
		        	var value = body.find(".layui-table-click td[data-field='equPositionNum']").find("div").text();
		        	equName = body.find(".layui-table-click td[data-field='equName']").find("div").text();
		        	$("#equNumber").val(value);
		        	layer.close(index); //如果设定了yes回调，需进行手工关闭
		        },
		  		btn3: function(index, layero){
		  			$("#equNumber").val("其它设备");
		  			equName = "其它设备";
		  			layer.close(index);
		  		},
		  		success: function(){
		  		
		  		}
		  });
	  });
})