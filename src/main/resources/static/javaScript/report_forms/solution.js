layui.use(['form', 'laypage', 'table', 'laydate', 'layer'], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var form = layui.form;
	var $ = layui.jquery;
	console.log(table);

	// 数据表格定义
	var solution_record_table;

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

	/**
	 * 监听导出按钮点击事件
	 */
	$("#search_button_export").on('click', function() {
		table.exportFile(solution_record_table.config.id,
				table.cache.solution_record_table);
		return false;
	});

	/**
	 * 监听打印按钮事件
	 */
	$("#search_button_print").on('click', function() {
				$(".layui-icon-print").trigger('click');
				return false;
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
					type : 'numbers',
					title : '序号'
				}, {
					title : '项目',
					field : 'PARA_NAME_CN'
				}]];
		$.each(allDay, function(index, item) {
					var cols_item = {};
					cols_item.title = item;
					cols_item.field = item;
					cols[0].push(cols_item);
				});
		// console.log(cols);
		solution_record_table = table.render({
					elem : '#solution_record_table',
					url : '/iot_inspection/processControlAnalysis/getRecord',
					where : {
						beginDate : beginDate,
						endDate : endDate,
						queryType : '1',// 1-列表数据请求
						paraType : '4'
					},
					toolbar : true,
					cellMinWidth : 110,
					height : 'full-160',
					title : '溶液周报',
					cols : cols,
					// page : true,
					// curr : 0,
					// limit : 5,
					// limits : [5, 10, 15, 20, 25, 30],
					layout : ['prev', 'page', 'next', 'skip', 'count', 'limit'],
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

});