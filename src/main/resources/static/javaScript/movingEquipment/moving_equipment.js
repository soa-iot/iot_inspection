layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;

	var currentTableHead;
	var currentTableBody;

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
			currentTableHead = res.data;

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

			// 渲染报表
			var moving_equipment_table = table.render({
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
				layout : ['prev', 'page', 'next', 'skip', 'count', 'limit'],
				done : function(res, curr, count) {
					currentTableBody = res.data;
				}
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
					},
					done : function(res, curr, count) {
						console.log("表格数据已刷新");
						currentTableBody = res.data;

					}
				});
		return false;
	});

	$('#search_button_export').on('click', function() {
				exportExcelBCE(currentTableHead, currentTableBody);
				return false;
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

	function exportExcelBCE(currentTableHead, currentTableBody) {
		console.log('导出报表按钮单击事件触发……');
		console.log(currentTableHead);
		console.log(currentTableBody);
		generStaticTable(currentTableHead, currentTableBody);
		// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，
		// 设置charset为urf-8以防止中文乱码
		var html = "<html><head><meta charset='utf-8' /></head><body>"
				+ $("#excelTempDiv").html() + "</body></html>";
		// 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
		var blob = new Blob([html], {
					type : "application/vnd.ms-excel"
				});
		$('body').append('<a id="aExport" style="display:none"></a>');
		var a = $('#aExport')[0];
		// 利用URL.createObjectURL()方法为a元素生成blob URL
		a.href = URL.createObjectURL(blob);
		// 设置文件名
		a.download = $('#inspection>option:checked').text() + '_' + $('#timeConponent').val()
				+ ".xls";
		document.getElementById("aExport").click();
		// $( '#aExport' ).click();
		return false;
	}

	/**
	 * 生成静态表格
	 */
	function generStaticTable(tableHeadData, tableBodyData) {

		/**
		 * 根据表头排序
		 */
		tableHeadData = tableHeadData.sort(function(a, b) {

					if (a.classNum == b.classNum) {
						return parseInt(a.sortNum) - parseInt(b.sortNum);
					} else {
						return parseInt(a.classNum) - parseInt(b.classNum);

					}

				});

		console.log(tableHeadData);

		console.log('生成静态表格……');
		var tableBefore = "<table>", tableEnd = "</table>";
		var tableBody = "";
		var colspan;
		var rowspan;

		var tr1 = "<tr>";
		var tr2 = "<tr>";

		$.each(tableHeadData, function(index, item) {
					console.log(item);
					colspan = item.colspan ? item.colspan : 1;
					rowspan = item.rowspan ? item.rowspan : 1;
					if (item.classNum == '1') {
						tr1 = tr1 + '<td colspan=' + colspan + ' rowspan='
								+ rowspan + ' align=' + item.align + '>'
								+ item.title + '</td>';
					} else if (item.classNum == '2') {
						tr2 = tr2 + '<td colspan=' + colspan + ' rowspan='
								+ rowspan + ' align=' + item.align + '>'
								+ item.title + '</td>';
					}
				});

		tr1 = tr1 + "</tr>";
		tr2 = tr2 + "</tr>";
		tableBody += tr1;
		tableBody += tr2;

		// console.log( '生成静态表格-生成表头……tableBody' + tableBody );

		/**
		 * 获取有序的filed
		 */

		/*
		 * var sort_field = []; $.each(tableHeadData, function(index, item) {
		 * 
		 * if (index.classNum == '1' && index.rolspan > 1) {
		 * sort_field.push(index); } }); $.each(tableHeadData, function(index,
		 * item) {
		 * 
		 * if (index.classNum == '2') { sort_field.push(index); } });
		 */

		/**
		 * 根据值排序
		 */

		class1_data = tableHeadData.filter(function(item) {
					return item.classNum == "1";
				});
		class2_data = tableHeadData.filter(function(item) {
					return item.classNum == "2";
				});
		class1_data = class1_data.sort(function(a, b) {
					return parseInt(a.sortNum) - parseInt(b.sortNum);

				});
		class2_data = class2_data.sort(function(a, b) {
					return parseInt(a.sortNum) - parseInt(b.sortNum);

				});

		var position = "", flagData = class1_data, num = 1;

		$.each(tableBodyData, function(index, item) {
					tableBody = tableBody + "<tr>";

					var begin = 0;
					for (var o in flagData) {
						position = flagData[o].field;

						if (flagData[o].colspan == 1) {
							if (item[position]) {
								tableBody = tableBody + '<td align="center">'
										+ item[position] + '</td>';
							} else {
								if (flagData[o].type == 'numbers') {
									tableBody = tableBody
											+ '<td align="center">' + num
											+ '</td>';
									num++;
								} else {
									tableBody = tableBody
											+ '<td align="center">' + ''
											+ '</td>';
								}
							}

						} else {
							for (var i = begin; i < flagData[o].colspan + begin; i++) {
								position = class2_data[i].field;
								if (item[position]) {
									tableBody = tableBody
											+ '<td align="center">'
											+ item[position] + '</td>';
								} else {
									if (flagData[o].type == 'numbers') {
										tableBody = tableBody
												+ '<td align="center">' + num
												+ '</td>';
										num++;
									} else {
										tableBody = tableBody
												+ '<td align="center">' + ''
												+ '</td>';
									}
								}
							}

							begin += flagData[o].colspan;
						}

					}
					/*
					 * $.each( flagData, function( index1, item1 ){ console.log(
					 * '生成静态表格……item.field' + item1.field ); position =
					 * item1[field]; tableBody = tableBody + '<td align="center">' +
					 * item[position] + '</td>'; } )
					 */
					tableBody = tableBody + "</tr>";
				})

		// console.log( '生成静态表格-生成数据……tableBody' + tableBody );
		$('body').append('<div id="excelTempDiv" style="display:none"></div>');
		$('#excelTempDiv').html('');
		$('#excelTempDiv').append(tableBefore + tableBody + tableEnd);
	}

});