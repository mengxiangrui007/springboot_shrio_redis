package com.abroad.common.comn;

import java.util.List;

/**
* @ClassName: Page
* @Description: TODO(页面类)
* @author: mengxr
* @date 2017年4月17日 上午10:23:52
*/
public class Page {

	/**
	 * @Fields currentPage : 当前页面
	 */
	private int currentPage;

	/**
	 * @Fields pageSize : 当前页面拥有条目数
	 */
	private int pageSize;

	/**
	 * @Fields pageCount : 分页数量
	 */
	private int pageCount;

	/**
	 * @Fields total : TODO(数据总条目数)
	 */
	private int total;
	/**
	 * @Fields resultList : 返回的数据载体
	 */
	private List<?> rows;

	/**
	 * <p>
	 * Title: 构造方法1
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param sum
	 *            总条目数
	 */
	public Page(int sum) {

	}

	/**
	 * <p>
	 * Title: 构造方法2
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param sum
	 *            总条目数
	 * @param size
	 *            当前页面数量
	 */
	public Page(int sum, int page, int size) {
		pageSize = size;
		total = sum;
		currentPage = page;
		init();
	}

	public void init() {
		pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize
				+ 1;
		// check page
		currentPage = currentPage < 1 ? 1
				: ((currentPage > pageCount) ? pageCount : currentPage);
	}

	/**
	 * @Title: getStart
	 * @Description: 得到start
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 温泉
	 * @date 2012-03-20 16:23:09 +0800
	 * @throws
	 */
	public int getStart() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return total;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int total) {
		this.total = total;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
