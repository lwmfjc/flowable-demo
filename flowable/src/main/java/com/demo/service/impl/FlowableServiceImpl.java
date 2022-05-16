package com.demo.service.impl;

import com.demo.service.FlowableService;
import liquibase.pro.packaged.A;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowableServiceImpl implements FlowableService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public void run(String processDefinitionKey, String demoInitiator, String demoAssignee, String businessId) {

        if (demoInitiator == null || "".equals(demoInitiator)
                || demoAssignee == null || "".equals(demoAssignee) || businessId == null || "".equals(businessId)) {
            System.out.println("参数缺失");
            return;
        }
        Map<String, Object> map = new HashMap<>();

        //在运行流程的时候就设置好发起人和网格员
        //map.put("demoInitiator", demoInitiator);
        map.put("demoAssignee", demoAssignee);
        //设置流程发起人
        Authentication.setAuthenticatedUserId(demoInitiator);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessId, map);
        Authentication.setAuthenticatedUserId(null);
        runtimeService.setVariable(processInstance.getId(), "demoAssignee",null);
        System.out.printf("流程id-%s\n", processInstance.getId());
    }

    @Override
    public List<Task> queryTask(String assignee) {
        //String assignee="id-w";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if (list.size() > 0) {
            for (Task task : list) {
                System.out.printf("任务id[%s]--任务名[%s]--发起时间[%s]\n", task.getId(), task.getName(), task.getCreateTime());
                String processInstanceId = task.getProcessInstanceId();
                ProcessInstance processInstance = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .singleResult();
                String startUserId = processInstance.getStartUserId();
                System.out.printf("发起人:[%s]\n" ,startUserId/*Authentication.getAuthenticatedUserId()*/);
            }
        } else {
            System.out.printf("没有任务需要处理!");
        }
        return list;
    }

    @Override
    public void completeTask(String taskId, String demoFlag, String demoAssignee, int demoLevel) {

        Task task = taskService.createTaskQuery()
                .taskId(taskId).singleResult();
        Map<String,Object> map=new HashMap<>();
        if (demoFlag != null && !"".equals(demoFlag)) {
            map.put("demoFlag", demoFlag);
        }
        map.put( "demoLevel", demoLevel);
        map.put( "demoAssignee", demoAssignee);

        runtimeService.setVariables(task.getExecutionId(),map);
        taskService.complete(task.getId());
        map.put("demoFlag", null);
        map.put( "demoLevel", -1);
        map.put( "demoAssignee", null);
        runtimeService.setVariables(task.getExecutionId(),map);
    }
}
