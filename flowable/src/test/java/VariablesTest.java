import com.demo.MyApplication;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class VariablesTest {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historicDataService;
    @Autowired
    private TaskService taskService;
    /**
     * 部署
     */
    @Test
    public void deploy( ) {
        //"demo-name1.bpmn20.xml"
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("asf.bpmn20.xml")
                .deploy();


    }
    @Test
    public void run(){
        String processDefinitionId="as:1:34cb7c42-d4e6-11ec-a0ba-28d0ea3a9c2a";
        Map<String,Object> map=new HashMap<>();
        map.put("demoAssignee","123");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, "businessId", map);
        Authentication.setAuthenticatedUserId(null);
    }

    @Test
    public void variableTask(){
        Map<String,Object> map=new HashMap<>();
        map.put("demoAssignee",666666666);
        //taskService.setVariable("45a8803d-d4e6-11ec-95bc-28d0ea3a9c2a","demoAssignee",4444);
        taskService.setVariablesLocal("45a8803d-d4e6-11ec-95bc-28d0ea3a9c2a",map);
    }

    @Test
    public void completeAllTask(){
        List<Task> list = taskService.createTaskQuery().list();
        for (Task task:list){
            taskService.complete(task.getId());
        }
    }
}
