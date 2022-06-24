package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Part {
    @TableId(type = IdType.INPUT)
    private String partno;
    private String partname;
    private String model;
    private Integer ct;

}
