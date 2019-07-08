package com.victorzhenwang.statemachinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatemachinedemoApplication{
//	@Autowired
//	private ApprovalStateMachineBuilder approvalStateMachineBuilder;
//
//	@Autowired
//	private FormStateMachineBuilder formStateMachineBuilder;
//
//	@Autowired
//	private BeanFactory beanFactory;
//
//	@Override
//	public void run(String... args) throws Exception {
//		StateMachine<FormStates, FormEvents> formStateMachine = formStateMachineBuilder.build(beanFactory);
//		StateMachine<States, Events> stateMachine = approvalStateMachineBuilder.build(beanFactory);
//
//		stateMachine.start();
//		stateMachine.sendEvent(Events.SUBMIT);
//		stateMachine.sendEvent(Events.APPROVE);
//
//		formStateMachine.start();
//
//		formStateMachine.sendEvent(FormEvents.WRITE);
//
//		formStateMachine.sendEvent(FormEvents.CONFIRM);
//
//		formStateMachine.sendEvent(FormEvents.SUBMIT);
//	}

	public static void main(String[] args) {
		SpringApplication.run(StatemachinedemoApplication.class, args);
	}


}
