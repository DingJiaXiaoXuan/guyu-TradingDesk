package com.guyu.utils;

public class Page {
    private Integer PageNum;    //当前页数
    private Integer count;      //当页显示条数
    private Integer start;      //开始位置
    private Integer point;      //结束位置
    private Integer PageSun;    //总页数
    private Integer countSun;   //总条数
    private Object data;        //数据

    public Page() {
    }

    public Page(Integer pageNum, Integer count) {
        this.PageNum = pageNum;
        this.count = count;
    }

    public Integer getPageNum() {
        return PageNum;
    }

    public void setPageNum(Integer pageNum) {
        PageNum = pageNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart() {
        this.start = (this.PageNum-1) * this.count;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint() {
        this.point = this.PageNum * this.count;
    }

    public Integer getPageSun() {
        return PageSun;
    }

    public void setPageSun() {
        if(this.countSun % this.count ==0){
            this.PageSun = this.countSun/this.count;
        }
        else {
            this.PageSun = this.countSun/this.count + 1 ;
        }
    }

    public Integer getCountSun() {
        return countSun;
    }

    public void setCountSun(Integer countSun) {
        this.countSun = countSun;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "PageNum=" + PageNum +
                ", count=" + count +
                ", start=" + start +
                ", point=" + point +
                ", PageSun=" + PageSun +
                ", countSun=" + countSun +
                ", data=" + data +
                '}';
    }
}

