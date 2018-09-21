package cn.cucsi.bsd.ucc.data.domain;

/**
 * 分页父BEAN
 */
public class Paging {

	protected int begin;// limit #{begin},#{showLines}
	
	protected int end;// �?般用不到 用showLines代替
	
	protected int showLines = 10;// 每页显示多少�?

	protected int yeShu;// 应该显示几页

	protected int onPage = 1;// 当前页号

	protected int nextPage = 0;// 下一�?
	
	protected int lastPage = 0;// 上一�?

	protected int allLines;// �?有行�?
	
	public static final int SHOW_LINES = 20;  //每页显示20行  Add by bli 2017-03-06
	
	public void setup(double allLines, double showLines) {
		
		if (showLines > 0) {
			this.showLines = (int) showLines;
		}
		
		this.onPage = this.onPage>0?onPage:1;
		
		this.allLines = (int) allLines;
		this.yeShu = (int) Math.ceil(allLines / showLines);
		if (this.onPage < this.yeShu) {
			this.nextPage = onPage + 1;
		}
		if (this.onPage > 1) {
			this.lastPage = onPage - 1;
		}
		
		begin = (int) showLines * (onPage - 1);
		
		if (showLines * onPage > allLines) {
			end = allLines < 0 ? 0 : (int) allLines;
		} else {
			end = showLines * onPage < 0 ? 0 : (int) showLines * onPage;
		}
	}
	
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getShowLines() {
		return showLines;
	}

	public void setShowLines(int showLines) {
		this.showLines = showLines;
	}

	public int getYeShu() {
		return yeShu;
	}

	public void setYeShu(int yeShu) {
		this.yeShu = yeShu;
	}

	public int getOnPage() {
		return onPage;
	}

	public void setOnPage(int onPage) {
		this.onPage = onPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getAllLines() {
		return allLines;
	}

	public void setAllLines(int allLines) {
		this.allLines = allLines;
	}

}
