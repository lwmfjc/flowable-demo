package com.demo.listen;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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
            HashMap map = (HashMap<String,Object>)delegateTask.getVariable("busiEntity");
            Object flowAssignee = map.get("flowAssignee");
            //如果设置了处理人
            if(null!=flowAssignee && !"".equals(flowAssignee)){
                delegateTask.setAssignee(flowAssignee.toString());
            }
        }
    }
}
