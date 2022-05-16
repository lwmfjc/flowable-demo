package com.demo.service;

import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlowableService {
     void run(String processDefinitionId,String demoInitiator, String demoAssignee, String businessId);

     /**
      * 查任务
      */
     List<Task> queryTask(String assignee);

     /**
      * 做任务
      */
     void completeTask(String taskId, String demoFlag, String demoAssignee,int
             demoLevel);
}
