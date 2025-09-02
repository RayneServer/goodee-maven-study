package dev.coma.study.common.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	private Long startIdx; // Limit의 첫 번째 파라미터
	private Long endIdx; // Limit의 두 번째 파라미터
	private Long perPage; // 페이지 당 보여줄 갯수 (= endIdx)
	private Long pageNum; // 출력할 Page의 번호
	private Long totalPage; // 전체 Page 수
	private Long startBlockNum; // 하단 페이지 블럭 시작 번호
	private Long endBlockNum; // 하단 페이지 블럭 끝 번호
	
	// for Search
	private String kind;
	private String keyword;
	
	private void calcPage() {
		this.startIdx = perPage * (pageNum - 1);
		this.endIdx = perPage;
	}
	
	public void initPage(Long totalCount) {
		this.getPerPage();
		this.totalPage = totalCount % perPage == 0 ? (totalCount == 0L ? 1 : (totalCount / perPage)) : (totalCount / perPage) + 1;
		this.getPageNum();
		
		Long perBlock = 5L;
		Long totalBlock = totalPage % perBlock == 0 ? (totalPage / perBlock) : (totalPage / perBlock) + 1;
		
		Long currentBlock = pageNum % perBlock == 0 ? (pageNum / perBlock) : (pageNum / perBlock) + 1;
		this.startBlockNum = (currentBlock - 1) * perBlock + 1;
		this.endBlockNum = currentBlock * perBlock;
		
		if (currentBlock == totalBlock) this.endBlockNum = totalPage;
		
		this.calcPage();
	}
	
	public Long getPageNum() {
		if (this.pageNum == null || this.pageNum < 1) this.pageNum = 1L;
		if (this.pageNum > totalPage) this.pageNum = totalPage;
		
		return pageNum;
	}
	
	public Long getPerPage() {
		if (this.perPage == null) this.perPage = 10L;
		
		return perPage;
	}
	
	public String getKeyword() {
		if (this.keyword == null) this.keyword = "";
		
		return keyword;
	}
}
