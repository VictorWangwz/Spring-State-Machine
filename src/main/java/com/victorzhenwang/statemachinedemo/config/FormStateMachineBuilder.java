package com.victorzhenwang.statemachinedemo.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class FormStateMachineBuilder {

    private final static String MACHINEID = "formMachine";

    public StateMachine<FormStates, FormEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<FormStates, FormEvents> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(FormStates.UNSIGNED_FORM)
                .states(EnumSet.allOf(FormStates.class));

        builder.configureTransitions()
                .withExternal()
                .source(FormStates.UNSIGNED_FORM).target(FormStates.CONFIRM_FORM)
                .event(FormEvents.APPROVE);
        return builder.build();
    }

}
