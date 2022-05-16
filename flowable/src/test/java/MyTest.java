
import com.demo.MyApplication;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MyTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historicDataService;
    @Autowired
    private TaskService taskService;
/*
    @Before
    public void before() {
        configuration =
                new StandaloneProcessEngineConfiguration();
        //配置
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        //nullCatalogMeansCurrent=true 设置为只查当前连接的schema库
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/flowable-demo?" +
                "useUnicode=true&characterEncoding=utf-8" +
                "&allowMultiQueries=true" +
                "&nullCatalogMeansCurrent=true");
        //如果数据库中表结构不存在则新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //获取ProcessEngine对象
        processEngine = configuration.buildProcessEngine();
        //获取服务
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();

        historicDataService = processEngine.getHistoryService();
        identityService = processEngine.getIdentityService();
        taskService = processEngine.getTaskService();
        formService = processEngine.getFormService();

    }*/

    /**
     * 部署
     */
    public void deploy(String fileName) {
        //"demo-name1.bpmn20.xml"
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource(fileName)
                .deploy();

    }

    /**
     * 运行流程实例
     */
    public void run(String processDefinitionId,String demoInitiator, String demoAssignee, String businessId) {
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
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessId, map);
        Authentication.setAuthenticatedUserId(null);
        System.out.printf("流程id-%s\n", processInstance.getId());
    }

    /**
     * 查任务
     */
    public void queryTask(String assignee) {
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
    }

    /**
     * 做任务
     */
    public void completeTask(String taskId, String demoFlag, String demoAssignee,int
            demoLevel) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId).singleResult();
        if (demoFlag != null && !"".equals(demoFlag)) {
            taskService.setVariable(task.getId(), "demoFlag", demoFlag);
        }
        if (demoAssignee != null && !"".equals(demoAssignee)) {
            taskService.setVariable(task.getId(), "demoAssignee", demoAssignee);
        }
        taskService.setVariable(task.getId(), "demoLevel", demoLevel);
        taskService.complete(task.getId());
    }

    @Test
    public void LyTest(){

        //非隐患驳回
       // deploy("demo-name1.bpmn20.xml");
        //run("demo-key1:1:b1f9d2de-d4c8-11ec-9120-28d0ea3a9c2a","id-f","id-w","busi-"+UUID.randomUUID().toString());
        //queryTask("id-w");
        //completeTask("3f266b66-d4c9-11ec-9373-28d0ea3a9c2a","0","",0);


        //deploy("demo-name1.bpmn20.xml");
        //run("demo-key1:1:b1f9d2de-d4c8-11ec-9120-28d0ea3a9c2a","id-f","id-w","busi-"+UUID.randomUUID().toString());
         queryTask("id-w");
        //completeTask("3f266b66-d4c9-11ec-9373-28d0ea3a9c2a","0","",0);
    }

}
