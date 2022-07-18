package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class EquipmentBom {
    private String equipid;
    private String equipname;
    private String processname;
    private String ct;
    private String level;
    private String levelnumber;
    private String processorder;
    private String part;
    private String model;

}
