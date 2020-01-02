var org_tree;

var treeResult, assignUsers = new Array(), ids = new Array();

layui.use([ 'tree', 'layer' ],
				function() {
					var tree = layui.tree, layer = layui.layer, $ = layui.jquery;

					$.ajax({
								url : '/iot_inspection/userOrganizationTree/userOrganizationTreeData',
								type : 'post',
								dataType : 'json',
								data : {},
								success : function(res) {

									if (res == 1) {
										layer.msg('服务器处理异常，请联系管理员！！！');
										return;
									}

									var data = buildTree(res.data);

									// 加载人员组织树
									org_tree = tree.render({
										elem : '#userTree',
										data : data,
										showCheckbox : true // 是否显示复选框
										,key : 'id',// 定义索引名称
										accordion : true // 是否开启手风琴模式
										,isJump : true
										,oncheck:function(obj){
											parseTree(obj);
											console.log(assignUsers);
										}
									});
									// 修改样式
									$('.layui-icon-file').addClass(
											"layui-icon-username");
									$('.layui-icon-file').removeClass(
											"layui-icon-file");

									console.log($('.layui-tree-icon'));

									$('.layui-tree-icon')
											.after(
													'<i class="my-icon layui-icon layui-icon-user" style = "font-size: 18px;margin-right:8px;color:#c0c4cc;;height: 100%;vertical-align: inherit;"></i>');

									$('#btn').on('click', function() {
										console.log(org_tree.getChecked());

									});

								},
								error : function() {
									layer.msg('请求失败，请联系管理员！！！');
								}
							});

});

/**
 * 获取选中的数据
 * 
 * @return {}
 */
function getCheckedData() {
	var checkData = org_tree.getChecked('id');
	return checkData;
}

/**
 * 解析树型结构,获取选中人员信息
 */
function parseTree(obj){
	 console.log(obj.data); //得到当前点击的节点数据
	 // console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
	 var data = obj.data;
	 //选中就添加人员
	 if(obj.checked){
		 getUser(data);
	 }else{
		 //去掉选中就删除人员
		 removeUser(data);
	 }
}

function getUser(data){
	if(data.children == null || data.children == undefined || data.children.length == 0){
		assignUsers.push(data.label);
		ids.push(data.id);
	 }else{
		for(var i=0;i<data.children.length;i++){
			getUser(data.children[i]);
		}
	 }
}

function removeUser(data){
	if(data.children == null || data.children == undefined || data.children.length == 0){
		for(var i=0;i<assignUsers.length;i++){
			if(assignUsers[i] == data.label){
				assignUsers.splice(i,1);
				ids.splice(i,1);
			}
		}
    }else{
    	for(var i=0;i<data.children.length;i++){
    		removeUser(data.children[i]);
		}
    }
}