package com.demo.listen;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class DemoTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        System.out.println("触发事件DemoTaskListener["+eventName+"]");
        System.out.println("taskName:"+delegateTask.getName()+"createTime:"
        +delegateTask.getCreateTime());
        //如果是创建事件
        if("create".equals(eventName)){
            Object demoAssignee = delegateTask.getVariable("demoAssignee");
            /*demoAssignee=(null==demoAssignee||"".equals(demoAssignee))?delegateTask
                    .getVariableLocal("demoAssignee"):demoAssignee;*/
            if(null!=demoAssignee && !"".equals(demoAssignee)){
                delegateTask.setAssignee(demoAssignee.toString());
                //execution.setE
            }
        }
    }
}
