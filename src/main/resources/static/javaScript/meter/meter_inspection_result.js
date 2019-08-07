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
				url : '/iot_inspection/meter/getSchemeInfo',
				type : 'post',
				async : false,
				data : {
					schemeType : '2'
				},
				dataType : 'json',
				success : function(res) {
					if (res.code != 0) {
						layer.msg("数据请求失败，请联系管理员！！！");
						return;
					}
					var option_html = "";
					$.each(res.data, function(index, item) {
								option_html += '<option value = "'
										+ item.schemeId + '">'
										+ item.schemeName + '</option>'

							});
					$('#inspection').html(option_html);
					form.render();

				},
				error : function() {
					layer.msg("数据请求失败，请联系管理员！！！");
				}
			});

	// 记录时间渲染
	laydate.render({
				elem : '#record_day',
				value : new Date()
			});

	/**
	 * 渲染表格
	 */
	loadTable($('#inspection').find('option:selected').text(),
			getNowFormatDate());

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
				console.log(scheme_name);
				console.log(record_day);

				loadTable(scheme_name, record_day);

				/**
				 * 重新渲染表格
				 */
				/*
				 * table.reload('moving_equipment_report', { url :
				 * '/iot_inspection/movingEquipment/getMovingEquipmentData',
				 * where : { schemeRemark : schemeRemark, date : date }, done :
				 * function(res, curr, count) { console.log("表格数据已刷新");
				 * currentTableBody = res.data; } });
				 */

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
	function loadTable(scheme_name, record_day) {

		switch (scheme_name) {
			case 'DCS、SIS、F&GS系统巡检方案' :
				$.ajax({
					url : '/iot_inspection/meter/getMeterInspectionResult',
					type : 'post',
					data : {
						schemeId : $('#inspection').val(),
						recordDay : record_day
					},
					dataType : 'json',
					success : function(res) {
						console.log(res);
						var data = res.data;
						var formatData = [];
						var theadData = [];
						$.each(data, function(index, item) {
							var formatData_item = {};
							formatData_item.requireContext = item.requireContext;
							formatData_item[item.contentName] = item.inspectionResult;
							formatData.push(formatData_item);
							if (!(theadData.indexOf(item.contentName) > -1)) {
								console
										.log(theadData
												.indexOf(item.contentName));
								theadData.push(item.contentName);
							}
						});

						var cols = [[{
									type : 'numbers',
									title : '序号',
									width : 80
								}, {
									field : 'requireContext',
									title : '巡检内容'
								}]];

						if (theadData.length == 0) {
							cols[0].push({
										field : 'inspectionResult',
										title : '巡检结果'
									});

						} else {
							$.each(theadData, function(index, item) {
										var thead_item = {};
										thead_item.field = item;
										thead_item.title = item;
										cols[0].push(thead_item);
									});

						}
						console.log(cols);

						var meter_table = table.render({
									elem : '#meter_record_table',
									data : formatData,
									toolbar : true,
									height : 'full-160',
									title : $('#inspection').val().replace(
											'巡检方案', '')
											+ '巡检记录_' + $('#record_day').val(),
									cols : cols,
									page : false,
									curr : 0,
									limit : 5,
									limits : [5, 10, 15, 20, 25, 30],
									layout : ['prev', 'page', 'next', 'skip',
											'count', 'limit'],
									done : function(res, curr, count) {
									}
								});
					},
					error : function() {
						layer.msg("服务器处理异常，请联系管理员！！！");

					}

				});

			case '' :
				break;
			default :
				// 渲染报表
				var meter_table = table.render({
					elem : '#meter_record_table',
					url : '/iot_inspection/meter/getMeterInspectionResult',
					where : {
						schemeId : $('#inspection').val(),
						recordDay : record_day
					},
					toolbar : true,
					height : 'full-160',
					title : $('#inspection').val().replace('巡检方案', '')
							+ '巡检记录_' + $('#record_day').val(),
					cols : [[{
								type : 'numbers',
								title : '序号',
								width : 80
							}, {
								field : 'requireContext',
								title : '巡检内容'
							}, {
								field : 'inspectionResult',
								title : '巡检结果'
							}]],
					page : false,
					curr : 0,
					limit : 5,
					limits : [5, 10, 15, 20, 25, 30],
					layout : ['prev', 'page', 'next', 'skip', 'count', 'limit'],
					done : function(res, curr, count) {
					}
				});
				break;

		}

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