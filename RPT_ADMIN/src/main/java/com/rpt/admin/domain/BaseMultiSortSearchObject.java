package com.rpt.admin.domain;

import java.util.List;
import java.util.Map;

/**
 * �??�� 모델 객체
 */
public class BaseMultiSortSearchObject extends BaseObject {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -688544270753013520L;

    /** �??��?��?��?�� */
    private String searchBgnDe = "";
    
    /** �??��종료?�� */
    private String searchEndDe = "";
    
    /** �??��조건 */
    private String searchCnd = "";
    
    /** �??��?��?�� */
    private String searchWrd = "";
    
    /** �??��IP */
    private String searchIp = "";
    
    /** ?��?��컬럼 */
    private String sortColumn = "";
   
    /** ?��?��?��?��(DESC,ASC) */
    private String sortOrdr = "";

    /** �??��?��?��?���? */
    private String searchUseYn = "";
    
    /** �??��분류(카테고리,?��?��, 조건(=,like)) */
    private String searchType = "";
    
    /** ?��?��?��?���? */
    private int pageIndex = 1;

    /** ?��?���?�??�� */
    private int pageUnit = 10;

    /** ?��?���??��?���? */
    private int pageSize = 10;

    /** 첫페?���? ?��?��?�� */
    private int firstIndex = 1;

    /** 마�?막페?���? ?��?��?�� */
    private int lastIndex = 1;

    /** ?��?���??�� ?��코드 개수 */
    private int recordCountPerPage = 10;

    /** ?��코드 번호 */
    private int rowNo = 0;
    
    /** ?��코드 �? �??�� */
    private int cnt = 0;
    
    private List<Map<String,String>> sort;
    
    private List<Map<String,String>> listMap;
    
	public List<Map<String, String>> getListMap() {
		return listMap;
	}
	public void setListMap(List<Map<String, String>> listMap) {
		this.listMap = listMap;
	}
	public List<Map<String, String>> getSort() {
		return sort;
	}
	public void setSort(List<Map<String, String>> sort) {
		this.sort = sort;
	}
	public String getSearchBgnDe() {
        return searchBgnDe;
    }
    public void setSearchBgnDe(String searchBgnDe) {
        this.searchBgnDe = searchBgnDe;
    }
    public String getSearchEndDe() {
        return searchEndDe;
    }
    public void setSearchEndDe(String searchEndDe) {
        this.searchEndDe = searchEndDe;
    }
    public String getSearchCnd() {
        return searchCnd;
    }
    public void setSearchCnd(String searchCnd) {
        this.searchCnd = searchCnd;
    }
    public String getSearchWrd() {
        return searchWrd;
    }
    public void setSearchWrd(String searchWrd) {
        this.searchWrd = searchWrd;
    }
    public String getSearchIp() {
        return searchIp;
    }
    public void setSearchIp(String searchIp) {
        this.searchIp = searchIp;
    }
    public String getSortColumn() {
        return sortColumn;
    }
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }
    public String getSortOrdr() {
        return sortOrdr;
    }
    public void setSortOrdr(String sortOrdr) {
        this.sortOrdr = sortOrdr;
    }
    public String getSearchUseYn() {
        return searchUseYn;
    }
    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }
    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageUnit() {
        return pageUnit;
    }
    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getFirstIndex() {
        return firstIndex;
    }
    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }
    public int getLastIndex() {
        return lastIndex;
    }
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }
    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }
    public int getRowNo() {
        return rowNo;
    }
    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }
    public int getCnt() {
        return cnt;
    }
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}