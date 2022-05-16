package com.demo.listen;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

@Component
public class TaskListen implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("taskName:"+delegateTask.getName()+"createTime:"
        +delegateTask.getCreateTime());
    }
}
