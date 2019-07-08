package com.victorzhenwang.statemachinedemo.config;

import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@WithStateMachine(id="formMachine")
public class FormEventConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApprovalStateMachineBuilder approvalStateMachineBuilder;

    @Resource(name="approvalFlowPersister")
    private StateMachinePersister<States, Events, ApprovalFlow> persister;

    @Autowired
    private BeanFactory beanFactory;

    @OnTransition(target = "UNSIGNED_FORM")
    public void create() {
        logger.info("---UNSIGNED_FORM---");
    }

    @OnTransition(source = "UNSIGNED_FORM", target = "CONFIRM_FORM")
    public void write(Message<FormEvents> message) throws Exception{
        String[][] stages = {
                {"zhen", "chuang", "da"}
        };
        StateMachine<States, Events> stateMachine = approvalStateMachineBuilder.build(beanFactory);
        ApprovalFlow flow = new ApprovalFlow("approvalFlow1", "TOAPPROVE", stages);
        persister.restore(stateMachine, flow);
        logger.info("" + stateMachine.getState().getId());
        printLog(message);
    }

    private void printLog(Message<FormEvents> message) {
        Object o = message.getHeaders().get("flow");
        try{
            Method m = o.getClass().getMethod("getId");
            logger.info("---{}: {}---", m.invoke(o),message.getPayload() );
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
