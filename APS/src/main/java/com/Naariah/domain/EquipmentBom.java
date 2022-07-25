package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class EquipmentBom {
    private String equipid;
    private String equipname;
    private String processname;
    private Integer ct;
    private Integer level;
    private Integer levelnumber;
    private Integer processorder;
    private String part;
    private String model;

}
