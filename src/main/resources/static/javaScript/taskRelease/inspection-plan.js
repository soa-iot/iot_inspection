
 layui.use(['table','tree', 'util','form','laydate'], function(){
  var table = layui.table
	,tree = layui.tree
    ,layer = layui.layer
    ,util = layui.util
	,form = layui.form
	,$ = layui.$
	,laydate = layui.laydate
	,shceme_list={}
    ,datatree=[]
  	,users = []
  	,role = {}
  	,plan_role_url = "http://192.168.18.116:10238";
  //,plan_role_url = "";
  
  laydate.render({
	    elem: '#plan_start_time'
	    ,type: 'datetime'
	    ,format:"yyyy-MM-dd HH:mm:ss"
	    ,done:function(value, date, endDate){
	    	 if (value == null || value == "") {
	    		 $("#plan_start_time_msg").show();
			}else{
				$("#plan_start_time_msg").hide();
			}
	    }
	  });
  
  $.ajax({
	  type:"post"
	  ,url:"/iot_inspection/meter/getSchemeInfo"
	  ,dataType:"json"
	  ,async:false
	  ,success:function(json){
		  
		  if (json.code==0) {
			  var data = json.data;
			  var op = '';
			$(data).each(function(index,element){
				shceme_list[element.schemeName] = element.schemeId
				op+='<option value="'+element.schemeName+'">'+element.schemeName+'</option>'
			})
			$("#scheme_name").append(op);
		}
	  }
  })
  
	
  console.log(shceme_list)
	var plan_table = table.render({
	    elem: '#plan_table'
	    ,url:"/iot_inspection/inspectionplan/inspectionplanall"
		,page:true
	    ,cols: [[ //标题栏
	      {field: 'rn', title: '序号',align:"center", width:'60'}
	      ,{field: 'planName', title: '计划名称',align:"center",width:'170'}
	      ,{field: 'schemeName', title: '方案名称',align:"center",width:'170'}
		  ,{field: 'equUnit', title: '装置单元',align:"center",width:'170'}
	      ,{field: 'planIntervalTime', title: '周期',align:"center",width:'170',templet:function (d) {
	    	  var unit ="";
	    		  if (d.remark=='H') {
	    			  unit='小时'
				}else if (d.remark=='D') {
	    			  unit='日'
				}
				else if (d.remark=='E') {
	    			  unit='周'
				}else if (d.remark=='M') {
	    			  unit='月'
				}
			  	return d.planIntervalTime+unit;
	          	
	      }}
	      ,{field: 'planStartTime',align:"center",width:'170', title: '启动时间',minWidth:'170'}
	      ,{field: 'planExpectTime',align:"center",width:'170',title: '计划完成时间',templet:function (d) {
	    	  var unit ="";
    		  if (d.remarkThree=='H') {
    			  unit='小时'
			}else if (d.remarkThree=='D') {
    			  unit='日'
			}
			else if (d.remarkThree=='E') {
    			  unit='周'
			}else if (d.remarkThree=='M') {
    			  unit='月'
			}
    		 var finishTime = d.planExpectTime == null ? "" : d.planExpectTime;
			  return finishTime+unit;
	          	
	      }}
	      ,{fixed: '',align:"center",width:'170',  title: '任务执行人', toolbar: '#barUsers'}
			,{fixed: 'right',align:"center",minWidth:'170',title: '操作', toolbar: '#barEdit'}
	    ]]
	  });
	  
	
	//监听行工具事件
	  table.on('tool(plan_table)', function(obj){
	    var data = obj.data;
		if (obj.event == "users") {
			get_role(obj)
			if (role.rolid == null || role.rolid == "") {
				my_ajax("post",{
					"name":data.planName
					,"state":0
					,"note":data.planName},plan_role_url+"/iot_usermanager/role/addOrUpdateRole");
				get_role(obj);
			}
			oppend();
		} else if (obj.event == "edit"){
			console.log("编辑")
			obj["title"]="编辑计划"
			add_or_edit(obj);
			plan_table.reload({
				page: {
					curr: 1 //重新从第 1 页开始
				}
			});
		} else if (obj.event == "del"){
			console.log("删除")
			get_role(obj);
			layer.msg('是否确认删除！！',{time: 10000,offset:"200px",btn:["是","否"],yes:function(index){
				
				if (role.rolid != null && role.rolid != "") {
					my_ajax("post",{
						"list[]":[""]
						,rolid: role.rolid
						},plan_role_url+"/iot_usermanager/role/addOrUpdateUserRole");
					my_ajax("get",{"ids":role.rolid},plan_role_url+"/iot_usermanager/role/deleteRoles");
				}
				
				var res = my_ajax("post",null,"/iot_inspection/inspectionplan/deleteplan/"+obj.data.planID);
				
				if (res.code == 0) {
					layer.msg('删除成功！！',{time: 3000,offset:"200px"});
					plan_table.reload({
						page: {
							curr: 1 //重新从第 1 页开始
						}
					});
				}else{
					layer.msg('删除失败！！',{time: 3000,offset:"200px"});
				}
			}});
			
		} else if (obj.event == "start"){
			console.log(obj);
			var data1 = obj.data;
			get_role(obj);
			if (data1.planIntervalTime == null || data1.planIntervalTime == "" || data1.planIntervalTime<0) {
				layer.msg('请添加大于0的任务周期！！',{icon:7,time: 3000,offset:"100px"});
				return;
			}else if (data1.remark != 'H'&& data1.remark != 'E'&& data1.remark != 'D'&& data1.remark != 'M') {
				layer.msg('请添加任务周期单位！！',{icon:7,time: 3000,offset:"100px"});
				return;
			}else if ( !/^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/.test(data1.planStartTime)) {
				layer.msg('请添加正确的启动时间！！',{icon:7,time: 3000,offset:"100px"});
				return;
			}
			if (role.rolid != null && role.rolid != "") {
				var res = my_ajax("post",null,"/iot_inspection/inspectionplan/planusers/"+role.rolid);
				
				if (res.data.length > 0 ) {
					var res1 = my_ajax("post",{
						"planID": obj.data.planID
						,"schemeID":obj.data.schemeID
						,"roleName":role.name
						,"schemeName":obj.data.schemeName
					},"/iot_inspection/inspectionplan/taskinspectionissued/"+role.rolid);
					layer.msg('计划执行成功！！',{icon:1,time: 3000,offset:"100px"});
				}else{
					layer.msg('请添加任务执行人！！',{icon:7,time: 3000,offset:"100px"});
				}
				//$(this).text("取消计划")
			}else {
				layer.msg('请添加任务执行人！！',{icon:7,time: 3000,offset:"100px"});
			}
		}
	      
	});
	
	  $("#query").click(function(){
		  
		  plan_table.reload({
			  where: { //设定异步数据接口的额外参数，任意设
				"planName":$("#panl_name_locate").val(),
				"schemeName":$("#scheme_name_locate").val(),
				"equUnit":$("#equ_unit_locate").val(),
				"planIntervalTime":$("#plan_cycle_locate").val(),
				"remark":$("#plan_cycle_unit_locate").val()
			  }
			  ,page: {
			    curr: 1 //重新从第 1 页开始
			  }
			});
	})
	
	$("#add_plan").click(function(){
		$('#add_or_edit_form')[0].reset()
		add_or_edit({title:"新增计划"});
	})
	
	function add_or_edit(d){
		
		  
		$(".msg").hide();
		var submit_data = {}
		if (d.event=="edit") {
			submit_data["planID"] = d.data.planID;
			$("#plan_name").val(d.data.planName);
			$("#plan_cycle").val(d.data.planIntervalTime);
			$("#plan_cycle_unit").val(d.data.remark);
			$("#equ_unit").val(d.data.equUnit);
			$("#plan_start_time").val(d.data.planStartTime);
			$("#plan_expect_time").val(d.data.planExpectTime);
			//$("#scheme_name").attr("disabled","disabled");
			$("#scheme_name").val(d.data.schemeName);
			$("#plan_desc").val(d.data.planDesc);
			$("#plan_expect_time_unit").val(d.data.remarkThree);
			form.render('select');
			
			get_role(d);
			console.log(role);
		}else{
			$("#scheme_name").attr("disabled",false);
			form.render('select');
		}
		
		layer.open({
			type: 1,
			title:d.title,
			area: ['700px', '400px;'],
			id: 'test10' + 1 //防止重复弹出
				,
			key: 'id',
			content: $("#add_or_edit"),
			btn: ['确认', "取消"],
			btnAlign: 'c', //按钮居中
			yes: function(index, layero) {
				var lag = true;
				submit_data["planName"] = $("#plan_name").val();               
				submit_data["planIntervalTime"] = $("#plan_cycle").val();      
				submit_data["remark"] = $("#plan_cycle_unit").val();           
				submit_data["equUnit"] = $("#equ_unit").val();                 
				submit_data["planStartTime"] = $("#plan_start_time").val();    
				submit_data["planExpectTime"] = $("#plan_expect_time").val();  
				submit_data["schemeName"] = $("#scheme_name").val();           
				submit_data["planDesc"] = $("#plan_desc").val();               
				submit_data["remarkThree"] = $("#plan_expect_time_unit").val();
				submit_data["schemeID"] = shceme_list[$("#scheme_name").val()];
				
				if ($.trim($("#plan_name").val()) == null || $.trim($("#plan_name").val()) == "") {
					$("#plan_name_msg").show();
					lag = false;
				}
				if($.trim($("#scheme_name").val()) == null || $.trim($("#scheme_name").val()) == ""){
					$("#scheme_name_msg").show();
					lag = false;
				}
				if($.trim($("#plan_cycle").val()) == null || $.trim($("#plan_cycle").val()) == ""){
					$("#plan_cycle_msg").text("*周期不能为空*");
					$("#plan_cycle_msg").show();
					lag = false;
				}else{
					$("#plan_cycle_msg").text("");
				}
				if(!/^\+?[1-9][0-9]*$/.test($("#plan_cycle").val())){
					$("#plan_cycle_msg").text("*周期必须大于0*");
					$("#plan_cycle_msg").show();
					lag = false;
				}else{
					$("#plan_cycle_msg").text("");
				}
				if($("#plan_cycle_unit").val() != "H" && $("#plan_cycle_unit").val() != "D" && $("#plan_cycle_unit").val() != "E" && $("#plan_cycle_unit").val() != "M"){
					$("#plan_cycle_msg").text($("#plan_cycle_msg").text() + "*请选择单位*");
					$("#plan_cycle_msg").show();
					lag = false;
				}
				if($("#plan_start_time").val() == null || $("#plan_start_time").val() == ""){
					$("#plan_start_time_msg").show();
					lag = false;
				}
				
				if(lag){
					var res = my_ajax("post",submit_data,"/iot_inspection/inspectionplan/addoreditinspectionplan");
					
					plan_table.reload({
						page: {
							curr: 1 //重新从第 1 页开始
						}
					});
					layer.close(index);
					layer.msg(d.title+'成功！！',{icon:1,time: 3000,offset:"100px"})
					if (d.title=="新增计划") {
						
						my_ajax("post",{
							"name":submit_data["planName"]
						,"state":0
						,"note":submit_data["planName"]},plan_role_url+"/iot_usermanager/role/addOrUpdateRole");
					}else{
						my_ajax("get",{
							"rolid":role.rolid
							,"name":submit_data["planName"]
						,"state":0
						,"note":submit_data["planName"]},plan_role_url+"/iot_usermanager/role/addOrUpdateRole");
					}
				}
			},
			success: function() {
			}
		});
		
	}
	
	  
	  
	  
	function oppend(){
		
		$.ajax({
			  type:"post"
			  ,url:"/iot_inspection/inspectionplan/usertree"
			  ,dataType:"json"
			  ,data:{"roleID":role.rolid}
			  ,async:false
			  ,success:function(json){
				  
				  $("#user_tree").empty();
				  if (json.code==0) {
					  console.log(json.data)
					  
					  //树的配置
					  var setting = {
						  check: {
					            enable: true	
					        },
					        data:{
					        	simpleData:{
					        		enable: true,
					    			idKey: "id",
					    			pIdKey: "parentID"
					        	}
					        }
					  };
					  
					  $.fn.zTree.init($("#user_tree"), setting, json.data);
					  
					  console.log("---------------------") 
					  
				}
			  }
		  })
			
		layer.open({
			type: 1,
			title:"人员组织信息",
			area: ['300px', '400px;'],
			id: 'user_tree_div' + 1 ,//防止重复弹出
			key: 'id',
			content: $("#user_tree_div"),
			btn: ['确认', "取消"],
			btnAlign: 'c', //按钮居中
			yes: function(index, layero) {
				
				layer.close(index);
				//获得选中的节点
				var checkData = ""//tree.getChecked('user_tree');
				
				users.length = 0;
				users[0] = "";
				var user_list = get_users(checkData);
				
				var role_add_user = my_ajax("post",{
					"list[]":user_list
					,rolid: role.rolid
					},plan_role_url+"/iot_usermanager/role/addOrUpdateUserRole");
				console.log(role_add_user);
				
			},
			success: function() {
			}
		});
	}
	
	//获取当前数据角色
	function get_role(d){
		var roles = my_ajax("get",{
			"roleName":d.data.planName
			},plan_role_url+"/iot_usermanager/role/rolename");
		
		if(roles.data.length>0){
			$(roles.data).each(function(index,element){
				if (element.name==d.data.planName) {
					role=element;
					return;
				}else{
					role.rolid = "";
				}
			})
		}else{
			role.rolid = "";
		}
		console.log(role);
	}
	
	//ajax
	function my_ajax(type, data, url){
		var res ={};
		$.ajax({
			  type:type
			  ,url:url
			  ,data:data
			  ,dataType:"json"
			  ,async:false
			  ,success:function(json){
				  res=json;
				  console.log(res);
			  }
		  })
		 return res;
	}
	
	//获取组织中选中的人
	function get_users(tree_user){
		
		var treeObj=$.fn.zTree.getZTreeObj("user_tree"),
        nodes=treeObj.getCheckedNodes(true),
        v=[""];
        for(var i=0;i<nodes.length;i++){
        	if (nodes[i].isParent==0) {
				v[v.length] = nodes[i].name;
			}
        }
        console.log("节点id:"+v); //获取选中节点的值
		
		return v;
	}
	
	
	//验证
	
	$("#plan_name").blur(function(){
		if ($.trim($("#plan_name").val()) == null || $.trim($("#plan_name").val()) == "") {
			$("#plan_name_msg").show();
		}else{
			$("#plan_name_msg").hide();
			$("#scheme_name_msg").hide();
		}
	})
	
	$("#plan_start_time").blur(function(){
		if ($.trim($("#plan_start_time").val()) == null || $.trim($("#plan_start_time").val()) == "") {
			$("#plan_start_time_msg").show();
		}else{
			$("#plan_start_time_msg").hide();
		}
	})
	$("#plan_cycle").blur(function(){
		if ($.trim($("#plan_cycle").val()) == null || $.trim($("#plan_cycle").val()) == "") {
			$("#plan_cycle_msg").text("*周期不能为空*");
			$("#plan_cycle_msg").show();
		}else if(!/^\+?[1-9][0-9]*$/.test($("#plan_cycle").val())){
			$("#plan_cycle_msg").text("*周期必须大于0*");
			$("#plan_cycle_msg").show();
		}else{
			$("#plan_cycle_msg").text("");
			$("#plan_cycle_msg").hide();
		}
	})
  });