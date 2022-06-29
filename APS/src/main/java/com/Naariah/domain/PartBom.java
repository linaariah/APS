package com.Naariah.domain;
import lombok.Data;

@Data
public class PartBom {
    private Integer id;
    private String partM;
    private String partMName;
    private String partS;
    private String partSName;
    private Double quantity;
    private boolean split;
    private String model;
}
