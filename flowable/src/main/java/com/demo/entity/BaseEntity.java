package com.demo.entity;

import lombok.Data;

@Data
public class BaseEntity {
    private String flowGatewayFlag;//网关标记
    private String flowAssignee;//指定下一个处理人
   // private String flowInitiator;//发起人
    private String businessId;//表单id
    private String createUser; //发起人,表单创建人
}
