import lombok.val;
import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Before;
import org.junit.Test;

public class MyTest {

    ProcessEngineConfiguration configuration = null;

    ProcessEngine processEngine = null;

    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private HistoryService historicDataService;
    private IdentityService identityService;
    private TaskService taskService;
    private FormService formService;

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

    }

    @Test
    public void deploy(){
        repositoryService.createDeployment()
                .addClasspathResource("");
    }
}
