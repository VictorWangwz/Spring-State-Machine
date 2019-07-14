package com.victorzhenwang.statemachinedemo;

import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class StatemachinedemoApplicationTests extends BaseTest{

	@Autowired
	private ApprovalFlow flow;

	@Test
	public void flowTest() {
		System.out.println("FLow: "+flow.getId());
	}

}
