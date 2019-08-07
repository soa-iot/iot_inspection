layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;

	// 数据表格定义
	var clean_gas_record_table;

	var date_picker = new Vue({
				el : '#app',
				data : {
					value1 : getMonday(new Date())
				},
				methods : {}
			});

	loadTable(getMonday(new Date()), getDateStr(6,
					getDate(getMonday(new Date()))));

	// 监听查询按钮点击事件
	form.on('submit(search_button_search)', function(data) {

				var beginDate = date_picker._data.value1;
				var endDate = getDateStr(6, getDate(beginDate));
				console.log(endDate);
				loadTable(beginDate, endDate)
				return false;
			});
	// 监听工具条
	table.on('toolbar(clean_gas_record_table)', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
				var layEvent = obj.event;
				if (layEvent === 'save') { // 保存

					clean_gas_record_table.config.LAY_CHECKED = true;
					var edit_data = table.cache[obj.config.id]
					console.log(edit_data);
					edit_data = format_data(edit_data);
					console.log(edit_data);
					if (edit_data == 'error') {
						layer.msg('数据格式不正确，请修改后再保存！！！', {
									icon : 2
								});
						return;
					}
					// 保存数据
					var save_msg = layer.msg('数据保存中。。。', {
						icon : 1,
						time : 2000
							// 2秒关闭（如果不配置，默认是3秒）
						}, function() {
						// do something
					});

					$.ajax({
						url : '/iot_inspection/processControlAnalysis/addUpdateRecord',
						type : 'post',
						data : JSON.stringify(edit_data),
						dataType : 'json',
						contentType : 'application/json;charset=utf-8',
						success : function(res) {
							console.log(res);
							if (res.code != 0) {
								layer.close(save_msg);
								layer.msg("保存数据失败，请联系管理员！！！");
								return;
							}
							var beginDate = date_picker._data.value1;
							var endDate = getDateStr(6, getDate(beginDate));
							console.log(endDate);
							loadTable(beginDate, endDate)
							layer.msg("保存数据成功");
						},
						error : function() {
							layer.close(save_msg);
							layer.msg("保存数据失败，请联系管理员！！！");
						}
					});

					// layer.close(save_msg);

				} else if (layEvent === 'del') { // 删除

					if (table.checkStatus(obj.config.id).data.length < 1) {
						layer.msg("请选择需要删除的数据", {
									icon : 2
								});
						return false;
					}

					layer.confirm('真的删除么？', function(index) {
						var checkStatus = table.checkStatus(obj.config.id);
						var del_data = [];
						$.each(checkStatus.data, function(index, item) {
							for (key in item) {
								var del_data_item = {};
								if (['PARA_NAME_CN', 'PARA_UNIT', 'GAS_CLASS1',
										'GAS_CLASS2', 'PARA_ID', 'ID']
										.indexOf(key) <= -1) {
									del_data_item.PARA_NAME_CN = item.PARA_NAME_CN;
									del_data_item.PARA_UNIT = item.PARA_UNIT;
									del_data_item.GAS_CLASS1 = item.GAS_CLASS1;
									del_data_item.GAS_CLASS2 = item.GAS_CLASS2;
									del_data_item.PARA_ID = item.PARA_ID;
									del_data_item.RECORD_DAY = key;
									del_data_item.RECORD_NUM = item.RECORD_NUM;
									del_data_item.PARA_VALUE = item[key];
									del_data.push(del_data_item);
								}
							}
						});

						console.log(del_data);

						layer.close(index);
						// 向服务端发送删除指令
						$.ajax({
							url : '/iot_inspection/processControlAnalysis/delRecord',
							type : 'post',
							dataType : 'json',
							contentType : 'application/json;charset=utf-8',
							data : JSON.stringify(del_data),
							success : function(res) {
								if (res.code != 0) {
									layer.msg("删除数据失败，请联系管理员！！！");
									return;
								}
								var beginDate = date_picker._data.value1;
								var endDate = getDateStr(6, getDate(beginDate));
								loadTable(beginDate, endDate)
								layer.msg("删除数据成功！！！");

							},
							error : function() {
								layer.msg("删除数据失败，请联系管理员！！！");

							}

						});
					});
				}
			});

	table.on('edit(clean_gas_record_table)', function(obj) { // 注：edit是固定事件名，test是table原始容器的属性
				// lay-filter="对应的值"
				obj.tr.find('td[data-field ="' + obj.field + '"]').css('color',
						'blue');
				if (isNaN(obj.value)) {
					layer.msg("格式不正确，请重新填写！！！");
					obj.tr.find('td[data-field ="' + obj.field + '"]').css(
							'color', 'red');
				}

			});

	/** ***************************************function************************************ */

	/**
	 * 获取当然日起间隔天数的日期
	 */
	function getDateStr(AddDayCount, dd) {

		if (dd == null || dd == '') {
			dd = new Date();
		}

		dd.setDate(dd.getDate() + AddDayCount); // 获取AddDayCount天后的日期
		var year = dd.getFullYear();
		var mon = dd.getMonth() + 1; // 获取当前月份的日期
		var day = dd.getDate();
		return year + '-' + (mon < 10 ? ('0' + mon) : mon) + '-'
				+ (day < 10 ? ('0' + day) : day);
	}

	/**
	 * 获取两个日期之间的每一天
	 */
	function formatEveryDay(start, end) {
		var dateList = [];
		var startTime = getDate(start);
		var endTime = getDate(end);

		while ((endTime.getTime() - startTime.getTime()) >= 0) {
			var year = startTime.getFullYear();
			var month = startTime.getMonth() + 1 < 10 ? '0'
					+ (startTime.getMonth() + 1) : startTime.getMonth() + 1;
			var day = startTime.getDate().toString().length == 1 ? "0"
					+ startTime.getDate() : startTime.getDate();
			dateList.push(year + "/" + month + "/" + day);
			startTime.setDate(startTime.getDate() + 1);
		}
		return dateList;
	}

	function getDate(datestr) {
		var temp = datestr.split("-");
		var date = new Date(temp[0], temp[1] - 1, temp[2]);
		return date;
	}

	// 渲染表格
	function loadTable(beginDate, endDate) {
		var allDay = formatEveryDay(beginDate, endDate);
		// console.log(allDay);
		var cols = [[{
					type : 'checkbox'
				}, {
					type : 'numbers',
					title : '序号'
				}, {
					title : '项目',
					field : 'PARA_NAME_CN'
				}, {
					title : '单位',
					field : 'PARA_UNIT'
				}, {
					title : '一类气',
					field : 'GAS_CLASS1'
				}, {
					title : '二类气',
					field : 'GAS_CLASS2'
				}]];
		$.each(allDay, function(index, item) {
					var cols_item = {};
					cols_item.title = item;
					cols_item.field = item;
					cols_item.edit = 'text';
					cols[0].push(cols_item);
				});

		cols[0].push({
					title : 'min',
					field : 'min'
				}, {
					title : 'max',
					field : 'max'
				});
		// console.log(cols);
		clean_gas_record_table = table.render({
					elem : '#clean_gas_record_table',
					url : '/iot_inspection/processControlAnalysis/getRecord',
					where : {
						beginDate : beginDate,
						endDate : endDate,
						queryType : '1',// 1-列表数据请求
						paraType : '1'
					},
					toolbar : '#toolbar',
					cellMinWidth : 110,
					height : 'full-160',
					title : '净化气周报',
					cols : cols,
					// page : true,
					// curr : 0,
					// limit : 5,
					// limits : [5, 10, 15, 20, 25, 30],
					// layout : ['prev', 'page', 'next', 'skip', 'count',
					// 'limit'],
					parseData : function(res) { // res 即为原始返回的数据
						data = res.data;
						var formatdata = {};
						$.each(data, function(index, item) {
									if (formatdata[item.PARA_NAME_CN]) {
										// 此条数据存在
										var data_item = formatdata[item.PARA_NAME_CN];
										data_item.PARA_NAME_CN = item.PARA_NAME_CN;
										data_item.PARA_UNIT = item.PARA_UNIT;
										data_item.GAS_CLASS1 = item.GAS_CLASS1;
										data_item.GAS_CLASS2 = item.GAS_CLASS2;
										data_item.PARA_ID = item.PARA_ID;
										data_item.RECORD_NUM = item.RECORD_NUM;
										data_item.ID = item.ID;
										data_item[item.RECORD_DAY] = item.PARA_VALUE;
										if (data_item.max < item.PARA_VALUE) {
											data_item.max = item.PARA_VALUE;
										}
										if (data_item.min > item.PARA_VALUE) {
											data_item.min = item.PARA_VALUE;
										}
									} else {
										var data_item = {};
										data_item.PARA_NAME_CN = item.PARA_NAME_CN;
										data_item.PARA_UNIT = item.PARA_UNIT;
										data_item.GAS_CLASS1 = item.GAS_CLASS1;
										data_item.GAS_CLASS2 = item.GAS_CLASS2;
										data_item.PARA_ID = item.PARA_ID;
										data_item.ID = item.ID;
										data_item[item.RECORD_DAY] = item.PARA_VALUE;
										data_item.min = item.PARA_VALUE;
										data_item.RECORD_NUM = item.RECORD_NUM;
										data_item.max = item.PARA_VALUE;
										formatdata[item.PARA_NAME_CN] = data_item;
									}
								});
						console.log(formatdata);

						data = [];
						for (key in formatdata) {
							data.push(formatdata[key]);
						}

						return {
							"code" : res.code, // 解析接口状态
							"msg" : res.message, // 解析提示文本
							"count" : res.count, // 解析数据长度
							"data" : data
						};
					},
					done : function(res, curr, count) {

					}
				});
	}
	/**
	 * 根据周获取时间区间
	 */
	function getDateRange(_year, _week) {
		var beginDate;
		var endDate;
		if (_year == null || _year == '' || _week == null || _week == '') {
			return "";
		}
		beginDate = getXDate(_year, _week, 4);
		endDate = getXDate(_year, (_week - 0 + 1), 5);
		return {
			beginDate : getNowFormatDate(beginDate),
			endDate : getNowFormatDate(endDate)
		};
	}
	/**
	 * 格式化时间
	 * 
	 */
	function getNowFormatDate(theDate) {
		var day = theDate;
		var Year = 0;
		var Month = 0;
		var Day = 0;
		var CurrentDate = "";
		// 初始化时间
		Year = day.getFullYear();// ie火狐下都可以
		Month = day.getMonth() + 1;
		Day = day.getDate();
		CurrentDate += Year + "-";
		if (Month >= 10) {
			CurrentDate += Month + "-";
		} else {
			CurrentDate += "0" + Month + "-";
		}
		if (Day >= 10) {
			CurrentDate += Day;
		} else {
			CurrentDate += "0" + Day;
		}
		return CurrentDate;
	}

	/**
	 * 获取日期所在周的周一
	 */
	function getMonday(dd) {
		var week = dd.getDay(); // 获取时间的星期数
		var minus = week ? week - 1 : 6;
		dd.setDate(dd.getDate() - minus); // 获取minus天前的日期
		return getNowFormatDate(dd);
	}

	/**
	 * 格式化数据
	 */
	function format_data(data) {
		var format_data = [];
		var error = "";
		$.each(data, function(index, item) {
					for (key in item) {
						var format_data_item = {};

						// 日期格式匹配正则表达式
						var reg = /\d{4}[-/]?\d{2}[-/]?\d{2}/;
						if (key.match(reg)) {
							if (item[key]) {
								format_data.PARA_NAME_CN = item.PARA_NAME_CN;
								format_data_item.PARA_UNIT = item.PARA_UNIT;
								format_data_item.GAS_CLASS1 = item.GAS_CLASS1;
								format_data_item.GAS_CLASS2 = item.GAS_CLASS2;
								format_data_item.PARA_ID = item.ID;
								format_data_item.RECORD_DAY = key;
								format_data_item.RECORD_NUM = '1';
								format_data_item.PARA_VALUE = item[key];
								if (isNaN(item[key])) {
									error = "error";
									break;
								} else {
									format_data.push(format_data_item);
								}

							}
						}
					}

				});
		if (error == "error") {
			return error;
		} else {
			return format_data;
		}
	}

});