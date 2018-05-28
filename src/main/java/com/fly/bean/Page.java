package com.fly.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class Page<T> {
    private String msg;
    private Integer code;
    private List<T> data;
    private Long count;

    public Page(PageInfo<T> pageInfo,Integer code){
        this.data = pageInfo.getList();
        this.count = pageInfo.getTotal();
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
