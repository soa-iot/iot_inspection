layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;
	var currentTableHead;
	var currentTableBody;

	
	
	/**
	 * 方案名称渲染
	 */
	$.ajax({
		url: '/iot_inspection/meter/getSchemeInfo',
		type: 'post',
		async: false,
		data: {
			schemeType: '1'
		},
		dataType: 'json',
		success: function(res) {
			if (res.code != 0) {
				layer.msg("数据请求失败，请联系管理员！！！");
				return;
			}
			var option_html = "";
			$.each(res.data, function(index, item) {
				option_html += '<option value = "' +
					item.schemeId + '">' +
					item.schemeName + '</option>'

			});
			$('#inspection').html(option_html);
			form.render();

		},
		error: function() {
			layer.msg("数据请求失败，请联系管理员！！！");
		}
	});
	// 记录时间渲染
	laydate.render({
		elem: '#record_day',
		value: new Date()
	});

	/**3C8DBF9F3A8940578C5D196124194C69
	 * 渲染表格
	 * 45232EBE03A545F29D09277256C3B2AB
	 */
//		loadTable('3C8DBF9F3A8940578C5D196124194C69','2019-09-16');

	/**
	 * 监听查询按钮事件
	 */
	form.on('submit(search_button_search)', function(data) {
		/**
		 * 获取表单数据
		 */

		var scheme_name = $('#inspection').find('option:selected')
			.text();
		var record_day = $('#record_day').val();

		loadTable(scheme_name, record_day);
		//				loadTable('3C8DBF9F3A8940578C5D196124194C69','2019-09-16');
		/**
		 * 重新渲染表格
		 */
		return false;
	});

	/**
	 * 监听导出按钮事件
	 */
	$('#search_button_export').on('click', function() {
		// 点击了导出按钮
		table.exportFile(['名字','性别','年龄'], [
			  ['张三','男','20'],
			  ['李四','女','18'],
			  ['王五','女','19']
			],'xls','121212'); 
		//默认导出 csv，也可以为：xls
//		return false;
	});

	/**
	 * 渲染表格数据
	 * schemeId: $('#inspection').val(),
				recordDay: record_day
	 */
	function loadTable(scheme_name, record_day) {
		layer.load(1);
		$.post('/iot_inspection/ibricate/getIubricateResult', {
				schemeId: $('#inspection').val(),
				recordDay: record_day
			},
			function(res) {
				if (res.code == 0) {
					 layer.closeAll();
					table_render(res.data);
				}
			});

	}


	function table_render(data) {
		  table.render({
			    elem: '#meter_record_table',
			    data:data,
			    toolbar : true,  
				height : 'full-160',
				cols: tab_code.header($('#inspection').val()),
				page : true,
				curr : 0,
				limit : 20,
				limits : [ 10, 20, 30,40,50],
				layout : ['prev', 'page', 'next', 'skip',
						'count', 'limit'],
				done : function(res, curr, count) {
				}
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

	/**
	 * 使用循环的方式判断一个元素是否存在于一个数组中
	 * @param {Object} arr 数组
	 * @param {Object} value 元素值
	 */
	function isInArray(arr, value) {
		for (var i = 0; i < arr.length; i++) {
			if (value === arr[i]) {
				return true;
			}
		}
		return false;
	}

});