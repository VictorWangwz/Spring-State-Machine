package com.victorzhenwang.statemachinedemo.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class ApprovalStateMachineBuilder {
    private final static String MACHINEID = "approvalMachine";

    public StateMachine<States, Events> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(States.TOAPPROVE)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.TOAPPROVE).target(States.UNDERAPPROVING)
                .event(Events.SUBMIT).action(action())
                .and()
                .withExternal()
                .source(States.UNDERAPPROVING).target(States.APPROVED)
                .event(Events.APPROVE)
                .and()
                .withExternal()
                .source(States.UNDERAPPROVING).target(States.REJECTED)
                .event(Events.REJECT);

        return builder.build();
    }

    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println(context);
            }
        };
    }
}
