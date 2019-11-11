/**
 * js工具
 * 
 */
var iubricate_code_14 = 'E714C1CBC59D41499A7C07BB7E9FE994';
var iubricate_code_57 = '1EE18CF3A9AF46FB9691784471FE4F42';
var iubricate_code_3200 = '7F2558928ECB4C62A479874432D9C4A0';
var iubricate_code_gg = '8A742880175F4151BE84C60F2F076416';

var tab_code = {
	header : function(code) {
		var result = "";
		switch (code) {
		case iubricate_code_14:
			result = [ [ {
				type : 'numbers',
				title : '序号',
				width : 80,
				rowspan : 2
			}, {
				field : 'TASK_CONTENT_NAME',
				title : '设备位号',
				rowspan : 2
			}, {
				field : 'EQU_NAME',
				title : '设备名称',
				rowspan : 2
			}, {
				field : '是否在用',
				title : '是否在用',
				rowspan : 2
			}, {
				align : 'center',
				title : '前端（或齿轮箱）',
				colspan : 2
			}, {
				align : 'center',
				title : '后端（或曲轴箱）',
				colspan : 2
			} 
			, {
				field : '是否泄漏',
				title : '是否泄漏',
				rowspan : 2
			}, {
				field : 'TEXT',
				title : '问题及处理',
				rowspan : 2
			} ], [ {
				field : '前端（或齿轮箱）油位',
				title : '油位'
			}, {
				field : '前端（或齿轮箱）油质',
				title : '油质'
			}, {
				field : '后端（或曲轴箱）油位',
				title : '油位'
			}, {
				field : '后端（或曲轴箱）油质',
				title : '油质'
			} ] ]
			break;
		case iubricate_code_57:
			result = [ [ {
				type : 'numbers',
				title : '序号',
				width : 80,
				rowspan : 2
			}, {
				field : 'TASK_CONTENT_NAME',
				title : '设备位号',
				rowspan : 2
			}, {
				field : 'EQU_NAME',
				title : '设备名称',
				rowspan : 2
			}, {
				field : '是否在用',
				title : '是否在用',
				rowspan : 2
			}, {
				align : 'center',
				title : '前端（或齿轮箱）',
				colspan : 2
			}, {
				align : 'center',
				title : '后端（或曲轴箱）',
				colspan : 2
			} , {
				align : 'center',
				title : '风机增速机',
				colspan : 2
			}, {
				align : 'center',
				title : '乙二醇',
				colspan : 2
			}
			, {
				field : '是否泄漏',
				title : '是否泄漏',
				rowspan : 2
			}, {
				field : 'TEXT',
				title : '问题及处理',
				rowspan : 2
			} ], [ {
				field : '前端（或齿轮箱）油位',
				title : '油位'
			}, {
				field : '前端（或齿轮箱）油质',
				title : '油质'
			}, {
				field : '后端（或曲轴箱）油位',
				title : '油位'
			}, {
				field : '后端（或曲轴箱）油质',
				title : '油质'
			}, {
				field : '风机增速机油位',
				title : '油质'
			}, {
				field : '风机增速机油质',
				title : '油质'
			}, {
				field : '乙二醇前端',
				title : '前端'
			}, {
				field : '乙二醇后端',
				title : '后端'
			} ] ]
			break;
		case iubricate_code_3200:
			result = [ [ {
				type : 'numbers',
				title : '序号',
				width : 80,
				rowspan : 2
			}, {
				field : 'TASK_CONTENT_NAME',
				title : '设备位号',
				rowspan : 2
			}, {
				field : 'EQU_NAME',
				title : '设备名称',
				rowspan : 2
			}, {
				field : '是否在用',
				title : '是否在用',
				rowspan : 2
			}, {
				align : 'center',
				title : '前端',
				colspan : 2
			}, {
				align : 'center',
				title : '后端',
				colspan : 2
			} // minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
			, {
				field : '是否泄漏',
				title : '是否泄漏',
				rowspan : 2
			}, {
				field : 'TEXT',
				title : '问题及处理',
				rowspan : 2
			} ], [ {
				field : '前端油位',
				title : '油位'
			}, {
				field : '前端油质',
				title : '油质'
			}, {
				field : '后端油位',
				title : '油位'
			}, {
				field : '后端油质',
				title : '油质'
			} ] ]
			break;
		case iubricate_code_gg:
			result = [ [ {
				type : 'numbers',
				title : '序号',
				width : 80,
				rowspan : 2
			}, {
				field : 'TASK_CONTENT_NAME',
				title : '设备位号',
				rowspan : 2
			}, {
				field : 'EQU_NAME',
				title : '设备名称',
				rowspan : 2
			}, {
				field : '是否在用',
				title : '是否在用',
				rowspan : 2
			}, {
				align : 'center',
				title : '前端',
				colspan : 2
			}, {
				align : 'center',
				title : '后端',
				colspan : 2
			} 
			, {
				field : '是否泄漏',
				title : '是否泄漏',
				rowspan : 2
			}, {
				field : 'TEXT',
				title : '问题及处理',
				rowspan : 2
			} ], [ {
				field : '前端油位',
				title : '油位'
			}, {
				field : '前端油质',
				title : '油质'
			}, {
				field : '后端油位',
				title : '油位'
			}, {
				field : '后端油质',
				title : '油质'
			} ] ]
			break;
		}
		return result;
	}
}