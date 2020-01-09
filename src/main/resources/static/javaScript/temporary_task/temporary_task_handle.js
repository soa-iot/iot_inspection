/**
 * 临时任务处理
 */
//var taskID = "1B1675C41F964871A2C240778D660DAD";
var taskID = getQueryUrlString("taskID");
var userAccount = "李某某";
layui.use(['layer', 'form', 'laydate', 'table', 'upload'], function(){
	
	var layer = layui.layer
		,laydate = layui.laydate
	    ,form = layui.form
	    ,upload = layui.upload
	    ,table = layui.table;
	
	//异步请求临时任务
	$.ajax({
		type: "GET",
		url: "/iot_inspection/temporarytask/task/detail",
		data: { 
			"taskID": taskID
		},
		dataType: "json",
		success: function(json){
			var data = json.data;
			if(json.state == 0 && data != null){
				$("#layui-row-equip").css("display", "none");
				
				if(data.taskState === 'FINISHED'){
					json.data.taskState = '已完成';
				}else if(data.taskState === 'UNFINISHED'){
					json.data.taskState = '未完成';
				}else if(data.taskState === 'OVERFINISHED'){
					data.taskState = '超期未完成';
				}
				
				$("#createPerson").val(data.createPerson);
				$("#departmentName").val(data.departmentName);
				$("#createTime").val(data.createTime);
				if(data.taskType == 0){
					$("#taskType").val('单人任务');
				}
				if(data.taskType == 1){
					$("#taskType").val('多人任务');
				}
				if(data.equNumber.match("其它") == null){
					$("#layui-row-equip").css("display", "block");
					$("#equNumber").val(data.equNumber);
			    	$("#equName").val(data.equName);
				}
				$("#requireFinishTime").val(data.requireFinishTime);
				$("#executePerson").val(data.executePerson);
				$("#taskState").val(data.taskState);
				$("#actualFinishTime").val(data.actualFinishTime);
				$("#taskName").val(data.taskName);
				$("#taskContent").val(data.taskContent);
				$("#riskWarning").val(data.riskWarning);
			}
		},
		error: function(){
			layer.msg("连接服务器失败",{icon: 2, offset:'100px'});
		}
	})
	
	//异步请求文件列表
	var fileList = $("#fileList");
	$.ajax({
		type: "GET",
		url: "/iot_inspection/temporarytask/file/showlist",
		data: { 
			"taskID": taskID,
			"fileClass": 0
		},
		dataType: "json",
		success: function(json){
			if(json.state == 0){
				if(json.data != null && json.data.length != 0){
					$("#fileTable").css("display", "block");
					for(var i=0;i<json.data.length;i++){
						var tr = $(['<tr>'
	        		          ,'<td>'+ json.data[i].fileOriginalName +'</td>'
	        		          ,'<td>'+ json.data[i].uploadPerson +'</td>'
	        		          ,'<td>'
	        		            ,'<a href="'+'/iot_inspection/temporarytask/file/download/?fileName='+json.data[i].fileOriginalName+'&filePath='+json.data[i].filePath+'"><button type="button" class="layui-btn layui-btn-xs layui-btn-normal">下载</button></a>'
	        		          ,'</td>'
	        		          ,'</tr>'].join(''));
  	        			fileList.append(tr);
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
	
	var fileListUpload=$("#fileList_upload"), fileObject={}; 
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
	    	$("#fileTable_upload").css("display", "block");

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
	          if(fileListUpload.children().length == 0){
	        	  $("#fileTable_upload").css("display", "none");
	          }
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        fileListUpload.append(tr);
	      });
	    }
	  });
	
	/**
	 * 任务完成按钮点击事件
	 */
	$("#saveBtn").click(function(){
		if($.trim($("#resultDescribe").val()) == ''){
			  layer.msg("完成描述不能为空", {icon: 7, offset: '150px'});
			  return;
		}
		
		//ajax异步保存临时任务
		var formData = new FormData();
		formData.append("taskID", taskID);
		formData.append("executePerson", userAccount);
		formData.append("resultDescribe", $.trim($("#resultDescribe").val()));

		//遍历上传的文件
		for(var index in fileObject){
			formData.append(index, fileObject[index]);
		}
		
		layer.confirm("是否确定完成任务?", function(){
			$.ajax({
	            url: "/iot_inspection/temporarytask/finish/task",
	            data: formData,
	            type: "POST",
	            dataType: "json",
	            processData: false,//用于对data参数进行序列化处理 这里必须false
	            contentType: false, //必须
	            success: function (json) {
	               if(json.state == 0){
	            	   layer.msg("完成任务成功",{icon: 1, offset:'100px'}); 
	            	   $("#resultDescribe").val("");

	            	   for(var index in fileObject){
	            		 delete fileObject[index];
	            	   }
	            	   $("#fileTable_upload").empty();
	           		   $("#fileTable_upload").css("display", "none");
	               }else{
	            	   layer.msg("任务完成失败",{icon: 2, offset:'100px'}); 
	               }
	            },
	            error: function(){
	            	layer.msg("连接服务器失败",{icon: 2, offset:'100px'});
	            }
	        })
		})

	})
})

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
    return context == null || context == "" || context == "undefined" ? "" : context;
}