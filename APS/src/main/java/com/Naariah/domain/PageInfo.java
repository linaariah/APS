package com.Naariah.domain;

import lombok.Data;

import java.util.List;
@Data
public class PageInfo {
    private Long current;      //当前页
    private Long pages;        //一共多少页
    private Long total;        //一共几条数据
    private List<RecordDetail> data;  //数据


}
