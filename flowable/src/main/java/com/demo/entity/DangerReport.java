package com.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DangerReport extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String remark;
    private int level;
}
