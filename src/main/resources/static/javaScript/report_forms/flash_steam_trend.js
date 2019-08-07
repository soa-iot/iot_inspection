$(function() {
	layui.use(['laydate', 'element', 'form', 'layer'], function() {
		var laydate = layui.laydate;
		var form = layui.form;
		var layer = layui.layer;
		var $ = layui.jquery;

		/**
		 * 渲染日期控件
		 */
		laydate.render({
					elem : '#begin_date',
					value : get_calculate(new Date(), 6)
				});
		laydate.render({
					elem : '#end_date',
					value : new Date()
				});

		/**
		 * 加载参数选择下拉选
		 */
		$.ajax({
					url : '/iot_inspection/processControlAnalysis/getParaConfig',
					type : 'post',
					async : false,
					data : {
						'paraType' : 2
					},
					dataType : 'json',
					success : function(res) {
						if (res.code != 0) {
							layer.msg("数据请求异常，请联系管理员！！！");
							return;
						}

						var params = [];
						var option_html = "";
						$.each(res.data, function(index, item) {
									if (index == 0) {
										params.push(item.paraNameCn);
									}
									option_html += "<option value = '"
											+ item.id + "'>" + item.paraNameCn
											+ "</option>"
								});
						console.log(option_html);
						$("#param").html(option_html);
						form.render();

					},
					error : function() {

					}
				});
		// console.log(dates);
		var beginDate = $('#begin_date').val();
		var endDate = $('#end_date').val();
		var paraId = $('#param').val();
		var params = [];
		params.push($('#param').find("option:selected").text());
		load_chart(beginDate, endDate, '2', paraId, params);// 1代表净化气

		$('#search').on('click', function() {
					var beginDate = $('#begin_date').val();
					var endDate = $('#end_date').val();
					var paraId = $('#param').val();
					params = [];
					params.push($('#param').find("option:selected").text());
					// console.log($('#param').val());
					load_chart(beginDate, endDate, '2', paraId, params);// 1代表净化气
				});

		/** *****************************************function******************************************************************************** */
		function load_chart(beginDate, endDate, paraType, paraId, params) {

			console.log(paraId);
			var xdata = [];
			var ydata = [];
			$.ajax({
				url : '/iot_inspection/processControlAnalysis/getRecord',
				type : 'get',
				data : {
					'beginDate' : beginDate,
					'endDate' : endDate,
					'paraType' : paraType,
					'queryType' : '2',// 2-图表数据请求
					'paraId' : paraId

				},
				dataType : 'json',
				success : function(res) {
					console.log(res);
					var xdata = [];
					var ydata = [];
					$.each(res.data, function(index, item) {
								xdata[index] = item.RECORD_DAY;
								ydata[index] = parseFloat(item.PARA_VALUE);
							});
					console.log(xdata);
					console.log(ydata);

					// 基于准备好的dom，初始化echarts图表
					var myChart = echarts.init(document.getElementById('main'));
					// 为echarts对象加载数据
					var option = {
						title : {
							text : '净化气趋势'
						},
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : params
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '3%',
							containLabel : true
						},
						toolbox : {
							feature : {
								saveAsImage : {}
							}
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : xdata
						},
						yAxis : {
							type : 'value'
						},
						series : get_series(params, ydata)
					};

					myChart.clear();
					myChart.setOption(option);
				},
				error : function() {
					layer.msg('数据请求异常，请联系管理员！！！');

				}
			});

		}

		function get_series(params, data) {
			var series_data = [];

			$.each(params, function(index, item) {
						var obj = {};
						obj.name = item;
						obj.type = 'line';
						obj.stack = item;
						obj.smooth = true;
						obj.markPoint = {
							data : [{
										name : '最大值',
										type : 'max'
									}, {
										name : '最小值',
										type : 'min'
									}]
						};
						obj.data = data;
						series_data.push(obj);
					});
			return series_data;
		}

	});
});
