package com.clouds.domain;

import java.util.List;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/9 10:55
 * @Version V1.0
 */
public class FilePage<T> {
    private int totalCount; //总记录数
    private int totalPage;  //总页数
    private List<T> files;  //当前页的数据
    private int pageNum;    //  当前页号
    private int rows;       //  每页显示记录数

    public FilePage() {
    }

    public FilePage(int totalCount, int totalPage, List<T> files, int pageNum, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.files = files;
        this.pageNum = pageNum;
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getFiles() {
        return files;
    }

    public void setFiles(List<T> files) {
        this.files = files;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
