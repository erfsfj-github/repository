package com.gcase.model.v1;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// <dependency>
//    <groupId>com.github.pagehelper</groupId>
//    <artifactId>pagehelper-spring-boot-starter</artifactId>
//    <version>1.2.12</version>
//</dependency>

//<dependency>
//    <groupId>com.alibaba</groupId>
//    <artifactId>fastjson</artifactId>
//    <version>1.2.74</version>
//</dependency>
/**
 * <p>
 * 分页模型
 * <p>
 *
 * @creator bicheng.deng
 * @createTime 2021/1/15
 */
public class PageModel<T> implements Serializable {

    private int code = 20000;

    private String msg = "";

    private int page = 0;

    private int limit = 10;

    private long count;

    private JSONObject condition;

    private List<T> data;

    private T obj;

    private String keywords;

    private String sorter;

    private String token;

    private Map<String,String> filter = new HashMap<>();

    public PageModel(String token, T obj) {
        this.token = token;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                ", count=" + count +
                ", data=" + data +
                ", keywords='" + keywords + '\'' +
                ", sorter='" + sorter + '\'' +
                '}';
    }

    public PageModel(List<T> list) {
        if (list instanceof Page) {
            Page<T> pageData = (Page<T>) list;
            this.page = pageData.getPageNum();
            this.limit = pageData.getPageSize();
            this.count = pageData.getTotal();
            this.data = pageData;
        }
    }
    public PageModel() {

    }

    public PageModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public JSONObject getCondition() {
        return condition;
    }

    public void setCondition(JSONObject condition) {
        this.condition = condition;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
