package com.demo.controller;

import com.demo.dto.ProcessDefinitionDto;
import com.demo.dto.ProcessInstanceDto;
import com.demo.http.HttpResult;
import com.demo.service.FlowableService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("process")
public class ProcessController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private FlowableService flowableService;

    @RequestMapping("run")
    public HttpResult run(@RequestParam("definiteKey") String definiteKey, @RequestParam("demoInitiator")  String demoInitiator, @RequestParam("demoAssignee") String demoAssignee,@RequestParam("businessId")  String businessId){
        flowableService.run(definiteKey,demoInitiator,demoAssignee,businessId);
        return HttpResult.ok();
    }

    @RequestMapping("list")
    public HttpResult list(){
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .list();
        List<ProcessInstanceDto> processInstanceDtos=new ArrayList<>();
        for(ProcessInstance processInstance:list){
            ProcessInstanceDto processInstanceDto=new ProcessInstanceDto();
            BeanUtils.copyProperties(processInstance,processInstanceDto);
            processInstanceDtos.add(processInstanceDto);
        }
        return HttpResult.ok(processInstanceDtos);
    }


}
