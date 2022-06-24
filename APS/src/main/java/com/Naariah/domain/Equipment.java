package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Equipment {
    @TableId(type = IdType.INPUT)
    private String equipmentno;
    private String equipmentname;
    private String processname;
    private Integer ct;
    private Integer processorder;
    private String partname;
    private String station;
    private String remark;

}
