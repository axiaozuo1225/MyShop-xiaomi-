package com.qf.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    //看前端页面图得到下面分页属性↓
    private int curPage;
    private int pageNum;
    private int totalCount;//最好封装为long类型,因为数据库返回的是long类型,否则容易出现类型转换异常
    private int totalPage;
    private List<T> list;

    public Page() {
    }

    public Page(int curPage, int pageNum, int totalCount, int totalPage, List<T> list) {
        this.curPage = curPage;
        this.pageNum = pageNum;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "curPage=" + curPage +
                ", pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
