package com.demo.controller;

import com.demo.dto.ProcessInstanceDto;
import com.demo.dto.TaskDto;
import com.demo.http.HttpResult;
import com.demo.service.FlowableService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务
 */
@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private FlowableService flowableService;

    @RequestMapping("todo/list")
    public HttpResult list(@RequestParam("assignee")String assignee){
        List<Task> tasks = flowableService.queryTask(assignee);
        List<TaskDto> taskDtos=new ArrayList<>();
        for(Task task:tasks){
            TaskDto taskDto=new TaskDto();
            BeanUtils.copyProperties(task,taskDto);
            taskDtos.add(taskDto);
        }
        return HttpResult.ok(taskDtos);
    }

    @RequestMapping("complete")
    public HttpResult run(@RequestParam("taskId") String taskId,@RequestParam("demoFlag") String demoFlag,@RequestParam("demoAssignee") String demoAssignee,@RequestParam("demoLevel")int
            demoLevel){
        flowableService.completeTask(taskId,demoFlag,demoAssignee,demoLevel);
        return HttpResult.ok();
    }


}
