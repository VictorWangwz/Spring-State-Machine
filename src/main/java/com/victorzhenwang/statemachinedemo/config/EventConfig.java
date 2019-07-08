package com.victorzhenwang.statemachinedemo.config;

import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.lang.reflect.Method;

@WithStateMachine(id="approvalMachine")
public class EventConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "TOAPPROVE")
    public void submit() {
        logger.info("---TOAPPROVE---");
    }


    @OnTransition(source = "TOAPPROVE", target = "UNDERAPPROVING")
    public void approving(Message<ApprovalFlow> message) throws Exception{
        printLog(message);
    }

    @OnTransition(source = "UNDERAPPROVING", target = "APPROVED")
    public void approve(Message<ApprovalFlow> message) throws Exception {
        printLog(message);
    }

    @OnTransition(source = "UNDERAPPROVING", target = "REJECTED")
    public void reject(Message<ApprovalFlow> message) throws Exception{
        printLog(message);
    }

    private void printLog(Message<ApprovalFlow> message) {
        Object o = message.getHeaders().get("flow");
        try{
            Method m = o.getClass().getMethod("getId");
            logger.info("---{}: {}---", m.invoke(o),message.getPayload() );
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

//    @OnTransition(source = "TOAPPROVE", target = "UNDERAPPROVING")
//    public void approving() {
//        logger.info("---UNDERAPPROVING---");
//    }

//    @OnTransition(source = "UNDERAPPROVING", target = "APPROVED")
//    public void approve() {
//        logger.info("---APPROVE---");
//    }
//
//    @OnTransition(source = "UNDERAPPROVING", target = "REJECTED")
//    public void reject() {
//        logger.info("---REJECT---");
//    }



//    @OnTransition(target = "TOAPPROVE")
//    public void submit(Message<Events> message) {
//        logger.info("---{}: {}---", message.getHeaders().getId(),message.getPayload() );
//    }



}
