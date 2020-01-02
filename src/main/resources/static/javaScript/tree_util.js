
/**
 * 
 * @param {}
 *            user_organizations
 * @return {}
 */
function buildTree(user_organizations) {

	var tree_data = [];

	var rootDatas = getRootNodes(user_organizations);

	$.each(rootDatas, function(index, item) {
				var node = {};
				node.id = item.name+','+item.is_parent+','+item.parent_id;
				node.label = item.name;
				node.children = getChildNodes(user_organizations, item);
				tree_data.push(node);
			});
	return tree_data;
}

/**
 * 获取子节点
 * 
 * @return {}
 */
function getChildNodes(user_organizations, user_organization) {
	var children = [];

	$.each(user_organizations, function(index, item) {
				if (item.parent_id == user_organization.usernum) {
					var node = {};
					node.id = item.name+','+item.is_parent+','+item.parent_id;
					node.label = item.name;
					node.children = getChildNodes(user_organizations, item);
					children.push(node);
				}
			});

	return children;
}

/**
 * 获取所有根节点
 * 
 * @param {}
 *            user_organizations
 * @return {}
 */
function getRootNodes(user_organizations) {

	var rootDatas = [];

	$.each(user_organizations, function(index, item) {
				if (rootNode(user_organizations, item)) {
					rootDatas.push(item);
				}
			});

	return rootDatas;

}

/**
 * 判断是否为根节点
 * 
 * @param {}
 *            user_organizations
 * @param {}
 *            user_organization
 * @return {}
 */
function rootNode(user_organizations, user_organization) {
	var isRootData = true;
	$.each(user_organizations, function(index, item) {
				if (user_organization.parent_id == item.usernum) {
					isRootData = false;
					return isRootData;
				}
			});

	return isRootData;
}
