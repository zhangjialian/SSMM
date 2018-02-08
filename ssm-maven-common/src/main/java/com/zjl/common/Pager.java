package com.zjl.common;

public class Pager {
	/**
	 * ҳ����Ŀ��
	 */
	private Integer pageSize;

	/**
	 * ��ǰҳ��
	 */
	private Integer currentPage;

	/**
	 * �ܵ���Ŀ��
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
	 * ��ȡ��ҳ��html����
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
			header += " <a href=\"javascript:doPage(" + (currentPage - 1) + ")\">��һҳ</a>";
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
			header += " <a href=\"javascript:doPage(" + (currentPage + 1) + ")\">��һҳ</a>";
		}
		header += "<span>��" + totalSize + "����¼</span>";
		header += hiddenPage;
		return header;
	}

	private int getTotalPageNum(int currentPage, int pageSize, int totalSize) {
		int totalPageCount = 1;
		if (pageSize == 0) {
			// ÿҳ��Ŀ���ô���Ĭ����ҳ��Ϊһ
			return totalPageCount;
		}
		if (totalSize == 0) {
			// ������Ϊ�㣬ֻ���е�һҳ
			return totalPageCount;
		}
		if (totalSize % pageSize == 0) {
			totalPageCount = totalSize / pageSize;
		} else {
			totalPageCount = totalSize / pageSize + 1;
		}

		return totalSize;
	}

	// ��ʼ����ҳ�ؼ��������ǰҳ��Ϊnull��0���趨Ϊ1,��pageSizeû�趨ʱ���趨������Ҫ�趨��pageSize
	public void initiPager(int pageSizeToSet) {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = pageSizeToSet;
		}
	}

}
