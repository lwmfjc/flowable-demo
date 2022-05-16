
import com.demo.MyApplication;
import org.apache.tomcat.jni.Proc;
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
    private ProcessEngine engine;
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
        runtimeService.setVariable(processInstance.getId(), "demoAssignee",null);
        System.out.printf("流程id-%s\n", processInstance.getId());
    }

    /**
     * 查任务
     */
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

    /**
     * 做任务
     */
    public void completeTask(String taskId, String demoFlag, String demoAssignee,int
            demoLevel) {
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

    @Test
    public void deleteAll(){
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for(Deployment deployment:list) {
            //删除部署
            repositoryService.deleteDeployment(deployment.getId(),true);
        }
         deploy("demo-name1.bpmn20.xml");
    }

    @Test
    public void LyTest(){
      //deleteAll();
        //非隐患驳回
        /*run("demo-key1:1:7cffa9ba-d4e2-11ec-babb-28d0ea3a9c2a","id-f","id-w","busi-"+UUID.randomUUID().toString());
         queryTask("id-w");
       completeTask("9256c4e5-d4e2-11ec-8414-28d0ea3a9c2a","0","",0);*/


        //指派人驳回
       //run("demo-key1:1:fd99de8b-d4ed-11ec-bd74-28d0ea3a9c2a","id-f","id-w","busi-"+UUID.randomUUID().toString());
          //queryTask("id-w");
         //通过并指派人
        //completeTask("1ce24b54-d4ee-11ec-a6e4-28d0ea3a9c2a","1","id-r",0);


        //指派人进行处理
        //run("demo-key1:1:fd99de8b-d4ed-11ec-bd74-28d0ea3a9c2a","id-f","id-w","busi-"+UUID.randomUUID().toString());
        //queryTask("id-w");
        //通过并处理[隐患重大]
        //completeTask("46b3dc03-d4ee-11ec-9dfe-28d0ea3a9c2a","1","xx",11);

    }

}
