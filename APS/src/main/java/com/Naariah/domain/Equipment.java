package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Equipment {
    @TableId(type = IdType.INPUT)
    private String equipid;
    private String equipname;
    private String workshopid;
    private String lineid;
    private String stationid;
    private String processname;
    private Integer ct;
    private double level;
    private double processorder;
    private String partname;
    private String remark;

}
