layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;

	var ins1 = laydate.render({
				elem : '#timeConponent',// 指定元素
				value : getNowFormatDate()
			});

	/**
	 * 发送ajax获取表头信息
	 */

	var cols = [];
	var index = layer.load();
	$.ajax({

		url : '/iot_inspection/movingEquipment/getHeaderInfo',
		type : 'post',
		data : {
			schemeType : '1'

		},
		dataType : 'json',
		success : function(res) {
			layer.close(index);
			var head_config = res.data;
			// console.log(head_config);

			var cols1 = [];
			var cols2 = [];

			$.each(head_config, function(index, item) {
						var cols_item = {};
						item.field != null && item.field != ''
								? (cols_item.field = item.field)
								: '';
						item.title != null && item.title != ''
								? (cols_item.title = item.title)
								: '';
						item.width != null && item.width != ''
								? (cols_item.width = item.width)
								: '';
						item.type != null && item.type != ''
								? (cols_item.type = item.type)
								: '';
						item.sort != null && item.sort != ''
								? (cols_item.sort = item.sort)
								: '';
						item.colspan != null && item.colspan != ''
								? (cols_item.colspan = item.colspan)
								: '';
						item.rowspan != null && item.rowspan != ''
								? (cols_item.rowspan = item.rowspan)
								: '';
						// console.log(JSON.stringify(cols_item));
						if (item.classNum == '1') {
							cols1[parseInt(item.sortNum) - 1] = cols_item;
						} else if (item.classNum == '2') {
							cols2[parseInt(item.sortNum) - 1] = cols_item;
						}

					});

			cols.push(cols1);
			cols.push(cols2);

			console.log($('#timeConponent').val());
			console.log($('#inspection').val());
			// 渲染报表
			table.render({
				elem : '#moving_equipment_report',
				url : '/iot_inspection/movingEquipment/getMovingEquipmentData?schemeRemark='
						+ $('#inspection').val()
						+ '&date='
						+ getNowFormatDate(),
				toolbar : true,
				height : 800,
				title : '动设备巡检记录',
				cols : cols,
				// page : true,
				curr : 0,
				limit : 5,
				limits : [5, 10, 15, 20, 25, 30],
				layout : ['prev', 'page', 'next', 'skip', 'count', 'limit']
			});

		},
		error : function() {

		}

	});

	/**
	 * 监听查询按钮事件
	 */
	form.on('submit(search_button_search)', function(data) {
		/**
		 * 获取表单数据
		 */

		var date = data.field.timeConponent;// 日期
		var schemeRemark = data.field.inspection;// 方案标识

		/**
		 * 重新渲染表格
		 */
		table.reload('moving_equipment_report', {
					url : '/iot_inspection/movingEquipment/getMovingEquipmentData',
					where : {
						schemeRemark : schemeRemark,
						date : date
					}
				});
		exportCsv2();
		return false;
	});

});

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

function exportCsv2() {
	// Excel打开后中文乱码添加如下字符串解决
	var exportContent = "\uFEFF";
	var blob = new Blob([exportContent + "标题,标题,标题\n1,2,3\n4,5,6"], {
				type : "text/plain;charset=utf-8"
			});
	saveAs(blob, "hello world.xlsx");
}
