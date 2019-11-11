layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;

	var currentTableHead;
	var currentTableBody;


	//常规用法
	  laydate.render({
	    elem: '#test1'
	  });
	//常规用法
	  laydate.render({
	    elem: '#test2'
	  });
  
  dadte('','','','');
  
  function dadte(eqbit,eqname,beginTime,finishTime) {
	  $.post('/iot_inspection/ibricate/getEquLubrication', {
		  'eqbit':eqbit
		  ,'eqname':eqname
		  ,'beginTime':beginTime
		  ,'finishTime':finishTime
		  }, function(results) {
			if(results.code==0){
				/**
				 * 渲染表格
				 */
				loadTable(results.data);
			}
			});
}
  

	/**
	 * 监听查询按钮事件
	 */
	form.on('submit(search_button_search)', function(data) {
				var eqbit = $('#eqbit').val();
				var eqname = $('#eqname').val();
				var beginTime = $('#beginTime').val();
				var finishTime = $('#finishTime').val();
				dadte(eqbit,eqname,beginTime,finishTime);
				return false;
			});

	/**
	 * 监听导出按钮事件
	 */
	$('#search_button_export').on('click', function() {
				// 点击了导出按钮
				return false;
			});

	/**
	 * 渲染表格数据
	 */
	function loadTable(data) {
	table.render({
    elem: '#test'
    ,data:data
    ,cols: [[
		{field:'LNAMEKEY',align : 'center', title: '设备位号',rowspan : 2},
		{field:'PPLACE',align : 'center', title: '润滑部位',rowspan : 2},
		{align : 'center', title: '润滑油(脂)',colspan : 2},
		{align : 'center', title: '数量',colspan : 2}
      ,{field:'RTIME',align : 'center', title: '时间',rowspan : 2}
      ,{field:'EXCUTOR',align : 'center', title: '操作人员',rowspan : 2}
      ,{field:'RNOTE',align : 'center',  title: '备注',rowspan : 2}
    ], [ 
//    	{
//    	field : 'ONAME',
//		align : 'center',
//		title : '油品',
//		width : 200
//		},
		{
			field : 'OSIGN',
			align : 'center',
			title : '牌号',
			width : 200
			},{
			field : 'MANUFACTURE',
			align : 'center',
			title : '生产厂家',
			width : 200
			},{
			field : 'SUPPLEMENT',
			align : 'center',
			title : '补充',
			width : 200
			},{
			field : 'EXCHANGES',
			align : 'center',
			title : '更换',
			width : 200
			} ] ]
  });

	}

	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}

});