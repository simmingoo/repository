package com.hsecure.model;

import java.util.List;
import java.util.Map;

public class BaseMultiSortSearchObject extends BaseObject
{
  private static final long serialVersionUID = -688544270753013520L;
  private String searchBgnDe = "";

  private String searchEndDe = "";

  private String searchCnd = "";

  private String searchWrd = "";

  private String searchIp = "";

  private String sortColumn = "";

  private String sortOrdr = "";

  private String searchUseYn = "";

  private String searchType = "";

  private int pageIndex = 1;

  private int pageUnit = 10;

  private int pageSize = 10;

  private int firstIndex = 1;

  private int lastIndex = 1;

  private int recordCountPerPage = 10;

  private int rowNo = 0;

  private int cnt = 0;
  private List<Map<String, String>> sort;
  private List<Map<String, String>> listMap;

  public List<Map<String, String>> getListMap()
  {
    return this.listMap;
  }
  public void setListMap(List<Map<String, String>> listMap) {
    this.listMap = listMap;
  }
  public List<Map<String, String>> getSort() {
    return this.sort;
  }
  public void setSort(List<Map<String, String>> sort) {
    this.sort = sort;
  }
  public String getSearchBgnDe() {
    return this.searchBgnDe;
  }
  public void setSearchBgnDe(String searchBgnDe) {
    this.searchBgnDe = searchBgnDe;
  }
  public String getSearchEndDe() {
    return this.searchEndDe;
  }
  public void setSearchEndDe(String searchEndDe) {
    this.searchEndDe = searchEndDe;
  }
  public String getSearchCnd() {
    return this.searchCnd;
  }
  public void setSearchCnd(String searchCnd) {
    this.searchCnd = searchCnd;
  }
  public String getSearchWrd() {
    return this.searchWrd;
  }
  public void setSearchWrd(String searchWrd) {
    this.searchWrd = searchWrd;
  }
  public String getSearchIp() {
    return this.searchIp;
  }
  public void setSearchIp(String searchIp) {
    this.searchIp = searchIp;
  }
  public String getSortColumn() {
    return this.sortColumn;
  }
  public void setSortColumn(String sortColumn) {
    this.sortColumn = sortColumn;
  }
  public String getSortOrdr() {
    return this.sortOrdr;
  }
  public void setSortOrdr(String sortOrdr) {
    this.sortOrdr = sortOrdr;
  }
  public String getSearchUseYn() {
    return this.searchUseYn;
  }
  public void setSearchUseYn(String searchUseYn) {
    this.searchUseYn = searchUseYn;
  }
  public String getSearchType() {
    return this.searchType;
  }
  public void setSearchType(String searchType) {
    this.searchType = searchType;
  }
  public int getPageIndex() {
    return this.pageIndex;
  }
  public void setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
  }
  public int getPageUnit() {
    return this.pageUnit;
  }
  public void setPageUnit(int pageUnit) {
    this.pageUnit = pageUnit;
  }
  public int getPageSize() {
    return this.pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public int getFirstIndex() {
    return this.firstIndex;
  }
  public void setFirstIndex(int firstIndex) {
    this.firstIndex = firstIndex;
  }
  public int getLastIndex() {
    return this.lastIndex;
  }
  public void setLastIndex(int lastIndex) {
    this.lastIndex = lastIndex;
  }
  public int getRecordCountPerPage() {
    return this.recordCountPerPage;
  }
  public void setRecordCountPerPage(int recordCountPerPage) {
    this.recordCountPerPage = recordCountPerPage;
  }
  public int getRowNo() {
    return this.rowNo;
  }
  public void setRowNo(int rowNo) {
    this.rowNo = rowNo;
  }
  public int getCnt() {
    return this.cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
}