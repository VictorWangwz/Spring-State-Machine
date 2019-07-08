package com.victorzhenwang.statemachinedemo.config;

import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

@Component
public class ApprovalFlowStateMachinePersist implements StateMachinePersist<States, Events, ApprovalFlow> {
    @Override
    public void write(StateMachineContext<States, Events> stateMachineContext, ApprovalFlow contextObj) throws Exception {

    }

    @Override
    public StateMachineContext<States, Events> read(ApprovalFlow contextObj) throws Exception {
        StateMachineContext<States, Events> result
                = new DefaultStateMachineContext<States, Events>(
                        States.valueOf(
                                contextObj.getState()),
                null,
                null,
                null,
                null,
                "approvalMachine"
        );
        return result;
    }
}
