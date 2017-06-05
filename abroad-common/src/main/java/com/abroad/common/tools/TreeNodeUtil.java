package com.abroad.common.tools;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abroad.common.comn.Tree;

/**
* @ClassName: TreeNodeUtil
* @Description: TODO(树工具类)
* @author: mengxr
* @date 2017年4月13日 下午4:55:38
*/
public class TreeNodeUtil {
	private static Logger log = LoggerFactory.getLogger(TreeNodeUtil.class);

	/**
	 * @Title: getfatherNode
	 * @Description 方法描述: 父节点
	 * @param 设定文件
	 *            ： @param treeDataList
	 * @param 设定文件
	 *            ： @return
	 * @return 返回类型：List<Tree>
	 * @throws
	 * @date 最后修改时间：2015年6月9日 下午6:39:26
	 */
	public final static List<Tree> getfatherNode(List<Tree> treeDataList) {
		List<Tree> newTreeDataList = new ArrayList<Tree>();
		for (Tree tree : treeDataList) {
			if (tree.getPid() == null) {
				// 获取父节点下的子节点
				tree.setChildren(getChildrenNode(tree.getId(), treeDataList));
				newTreeDataList.add(tree);
			}
		}
		return newTreeDataList;
	}
	/**
	* @Title: reverseTree
	* @Description: TODO(把List VO 转为 List<Tree>)
	* @param @param list
	* @param @return    设定文件
	* @return List<Tree>    返回类型
	* @author: mengxr
	* @date 2017年4月19日 下午2:10:33
	* @throws
	*/
	public  static List<Tree> reverseTree(List<?> list){
		List<Tree> newTree = null;
		try {
			if(null != list && list.size() > 0 ){
				newTree = new ArrayList<Tree>(list.size());
				Class<?> clazz = list.get(0).getClass();
				Tree tree = null;
				for(Object obj : list){
					tree = new Tree();
					tree.setId(String.valueOf(clazz.getMethod("getId").invoke(obj)));
					Long pid = (Long) clazz.getMethod("getPid").invoke(obj);
					tree.setPid(null != pid ? String.valueOf(pid) : null);
					tree.setCode((String)clazz.getMethod("getCode").invoke(obj));
					tree.setName((String)clazz.getMethod("getName").invoke(obj));
					newTree.add(tree);
				}
			}
		} catch (Exception e) {
			log.error("-----转换树形结构发生异常",e);
		} 
		return newTree;
	}
	/**
	 * @Title: getChildrenNode
	 * @Description 方法描述: 子节点
	 * @param 设定文件
	 *            ： @param pid
	 * @param 设定文件
	 *            ： @param treeDataList
	 * @param 设定文件
	 *            ： @return
	 * @return 返回类型：List<Tree>
	 * @throws
	 * @date 最后修改时间：2015年6月9日 下午6:39:50
	 */
	private final static List<Tree> getChildrenNode(String integer,
			List<Tree> treeDataList) {
		List<Tree> newTreeDataList = new ArrayList<Tree>();
		for (Tree tree : treeDataList) {
			if (tree.getPid() == null)
				continue;
			// 这是一个子节点
			if (tree.getPid().equals(integer)) {
				// 递归获取子节点下的子节点
				tree.setChildren(getChildrenNode(tree.getId(), treeDataList));
				newTreeDataList.add(tree);
			}
		}
		return newTreeDataList;
	}
}
