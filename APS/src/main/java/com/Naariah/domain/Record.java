package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class Record {
    @TableId(type = IdType.INPUT)
    private String productionNumber;
    private String orderNumber;
    private String customerName;
    private String model;
    private Integer number;
    private String date;
    private String createTime;

}

