package com.victorzhenwang.statemachinedemo.controller;

import com.victorzhenwang.statemachinedemo.config.*;
import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="approvalFlowInMemoryPersister")
    private StateMachinePersister<States, Events, String> approvalFlowInMemorypersister;

    @Resource(name="approvalFlowRedisPersister")
    private StateMachinePersister<States, Events, String> approvalFlowRedisPersister;

    @Resource(name="approvalFlowPersister")
    private StateMachinePersister<States, Events, ApprovalFlow> persister;


    @Autowired
    private ApprovalStateMachineBuilder approvalStateMachineBuilder;

    @Autowired
    private FormStateMachineBuilder formStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;


    @RequestMapping("/testApprovalFlowState")
    public void testApprovalFlowState() throws Exception {

        StateMachine<States, Events> stateMachine = approvalStateMachineBuilder.build(beanFactory);
        String[][] stages = {
                {"zhen", "chuang", "da"}
        };
        ApprovalFlow flow = new ApprovalFlow("approvalFlow1", "APPROVED", stages);

        logger.info(stateMachine.getId());

        stateMachine.start();

        Message<Events> message1 = MessageBuilder
                .withPayload(Events.SUBMIT)
                .setHeader("flow", flow)
                .build();
        stateMachine.sendEvent(message1);

        Message<Events> message2 = MessageBuilder
                .withPayload(Events.LOOP)
                .setHeader("flow", flow)
                .build();
        stateMachine.sendEvent(message2);

        logger.info("Final state：" + stateMachine.getState().getId());

        approvalFlowInMemorypersister.persist(stateMachine, flow.getId());
        logger.info("" + approvalFlowInMemorypersister.restore(stateMachine, flow.getId()).getState().getId());

        approvalFlowRedisPersister.persist(stateMachine, flow.getId());
        logger.info("" + approvalFlowRedisPersister.restore(stateMachine, flow.getId()).getState().getId());

        persister.restore(stateMachine, flow);
        logger.info("" + stateMachine.getState().getId());
    }

    @RequestMapping("/sign-form")
    public void testFormState() throws Exception {

        StateMachine<FormStates, FormEvents> formStateMachine = formStateMachineBuilder.build(beanFactory);
        System.out.println(formStateMachine.getId());

        formStateMachine.start();

        formStateMachine.sendEvent(FormEvents.APPROVE);

        logger.info("Final state：" + formStateMachine.getState().getId());
    }
}
