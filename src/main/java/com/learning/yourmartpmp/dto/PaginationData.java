package com.learning.yourmartpmp.dto;

/**
 * Model class for pagination details.
 * @author ayushsaxena
 *
 */
public class PaginationData {
	private boolean nextPage;
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
}
