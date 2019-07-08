package com.victorzhenwang.statemachinedemo.config;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryStateMachinePersist implements StateMachinePersist<States, Events, String> {

    private Map<String, StateMachineContext<States, Events>> map = new HashMap<String, StateMachineContext<States,Events>>();

    @Override
    public void write(StateMachineContext<States, Events> context, String contextObj) throws Exception {
        map.put(contextObj, context);
    }

    @Override
    public StateMachineContext<States, Events> read(String contextObj) throws Exception {
        return map.get(contextObj);
    }

}
