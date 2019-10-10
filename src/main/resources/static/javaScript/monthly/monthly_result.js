layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;

	var currentTableHead;
	var currentTableBody;

	dadte("2019");
	

  //年选择器
  laydate.render({
    elem: '#record_day'
    ,type: 'year'
  });


  function dadte(year) {
	  $.post('/iot_inspection/ibricate/getMonthlyResult', {'year':year}, function(results) {
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
				/**
				 * 获取表单数据
				 */
				var record_day = $('#record_day').val();
				dadte(record_day) 
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
		{align : 'right', title: '月份',colspan : 2}
      ,{field:'Jan', title: '一月',rowspan : 2}
      ,{field:'Feb', title: '二月',rowspan : 2}
      ,{field:'Mar',  title: '三月',rowspan : 2}
      ,{field:'Apr', title: '四月',rowspan : 2} 
      ,{field:'May', title: '五月',rowspan : 2}
      ,{field:'Jun', title: '六月',rowspan : 2}
      ,{field:'Jul', title: '七月',rowspan : 2}
      ,{field:'Aug' ,title: '八月',rowspan : 2}
	  ,{field:'Sep', title: '九月',rowspan : 2}
	  ,{field:'Oct',  title: '十月',rowspan : 2}
	  ,{field:'Nov',  title: '十一月',rowspan : 2}
	  ,{field:'Dec',  title: '十二月',rowspan : 2}
    ], [ {
			field : 'name',
			title : '油品名称',
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