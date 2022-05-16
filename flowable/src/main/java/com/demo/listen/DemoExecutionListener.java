package com.demo.listen;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DemoExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("触发事件DemoExecutionListener["+execution.getEventName()+"]");
    }
}
