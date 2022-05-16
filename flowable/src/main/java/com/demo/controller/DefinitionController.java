package com.demo.controller;

import com.demo.dto.ProcessDefinitionDto;
import com.demo.http.HttpResult;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("definition")
public class DefinitionController {
    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("list")
    public HttpResult list(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .list();
        List<ProcessDefinitionDto> processDefinitionDtos=new ArrayList<>();
        for(ProcessDefinition processDefinition:list){
            ProcessDefinitionDto processDefinitionDto=new ProcessDefinitionDto();
            BeanUtils.copyProperties(processDefinition,processDefinitionDto);
            processDefinitionDtos.add(processDefinitionDto);
        }
        return HttpResult.ok(processDefinitionDtos);
    }


}
