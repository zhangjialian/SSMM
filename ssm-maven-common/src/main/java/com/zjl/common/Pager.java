package com.zjl.common;

public class Pager {
	/**
	 * 页面条目数
	 */
	private Integer pageSize;

	/**
	 * 当前页码
	 */
	private Integer currentPage;

	/**
	 * 总的条目数
	 */
	private Integer totalSize;

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageHtml() {
		return generate(this.currentPage, this.totalSize, this.pageSize);
	}

	/**
	 * 获取分页的html代码
	 * @param currentPage
	 * @param totalSize
	 * @param pageSize
     * @return
     */
	public String generate(int currentPage, int totalSize, int pageSize) {
		int totalPageCount = getTotalPageNum(currentPage, pageSize, totalSize);
		String hiddenPage = "<input type='hidden' name='pageSize' value='" + pageSize + "'>"
				+ "<input type='hidden' name='resultCount' value='" + totalSize + "'>"
				+ "<input type='hidden' name='totallPageCount' value='" + totalPageCount + "'>";
		int pages = totalSize / pageSize;
		if (totalSize % pageSize > 0) {
			pages = pages + 1;
		}
		String header = "";
		if (currentPage > 1) {
			header += " <a href=\"javascript:doPage(" + (currentPage - 1) + ")\">上一页</a>";
		}
		int vstart;
		int end;
		if (currentPage <= 5) {
			vstart = 1;
			end = pages > 10 ? 10 : pages;
		} else if (currentPage >= (pages - 5)) {
			vstart = pages > 10 ? pages - 10 : 1;
			end = pages;
		} else {
			vstart = currentPage - 5;
			end = currentPage + 5;
		}
		for (int i = vstart; i <= end; i++) {
			if (i == currentPage) {
				header += "<span>" + currentPage + "</span>";
			} else {
				header += " <a href=\"javascript:doPage(" + i + ")\">" + i + "</a>";
			}
		}

		if (currentPage < pages - 10) {
			header += " <a href=\"javascript:doPage(" + pages + ")\">" + pages + "</a>";
		}

		if (currentPage < pages) {
			header += " <a href=\"javascript:doPage(" + (currentPage + 1) + ")\">下一页</a>";
		}
		header += "<span>共" + totalSize + "条记录</span>";
		header += hiddenPage;
		return header;
	}

	private int getTotalPageNum(int currentPage, int pageSize, int totalSize) {
		int totalPageCount = 1;
		if (pageSize == 0) {
			// 每页条目设置错误，默认总页数为一
			return totalPageCount;
		}
		if (totalSize == 0) {
			// 总条数为零，只会有第一页
			return totalPageCount;
		}
		if (totalSize % pageSize == 0) {
			totalPageCount = totalSize / pageSize;
		} else {
			totalPageCount = totalSize / pageSize + 1;
		}

		return totalSize;
	}

	// 初始化分页控件，如果当前页面为null或0则设定为1,当pageSize没设定时候，设定我们想要设定的pageSize
	public void initiPager(int pageSizeToSet) {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = pageSizeToSet;
		}
	}

}
