/**
 * 参数定义
 */
//项目ip
//var ip = "10.89.90.118",
var ip = "192.168.18.5",
	//项目端口
	port = "10238",
	//port = "8080",
	//域名
	ipPort = "http://" + ip + ":" + port + "/iot_inspection",
	//用户名
	currentUser = getCookie1( 'userName' ),
	//用户班组
	currentUserOrganization = getCookie1( 'userOrganization' ),
	//用户角色
	currentUserRole = getCookie1( 'userRole' );