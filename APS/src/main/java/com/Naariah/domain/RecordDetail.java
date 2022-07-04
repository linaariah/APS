package com.Naariah.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RecordDetail {

        private String productionNumber;
        private String equipid;
        private String equipname;
        private Integer number;
        private String starttime;
        private String endtime;
        private String model;


}
