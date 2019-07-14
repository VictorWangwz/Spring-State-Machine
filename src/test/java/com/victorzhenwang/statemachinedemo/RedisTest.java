package com.victorzhenwang.statemachinedemo;

import com.victorzhenwang.statemachinedemo.config.PersistConfig;
import com.victorzhenwang.statemachinedemo.dao.ApprovalFlowDao;
import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

import javax.inject.Inject;

@ContextConfiguration(classes = PersistConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Inject
    private StringRedisTemplate stringRedisTemplate;

    @Inject
    private ApprovalFlowDao approvalFlowDao;

    @Test
    public void testRedisTemplate(){
        assertNotNull(stringRedisTemplate);
    }

    @Test
    public void testAddWordWithItsMeaningToDictionary() {
        String[][] stages = {
                {"zhen"}
        };
        ApprovalFlow flow  = new ApprovalFlow("ApprovalFlowTest", "TOAPPROVE", stages);
        approvalFlowDao.addFlowToRedis(flow);
        System.out.println(stringRedisTemplate.hasKey("ApprovalFlowTest"));
        if(stringRedisTemplate.hasKey("ApprovalFlowTest")){
            String flowName = stringRedisTemplate.opsForValue().get(flow.getId());
            assertTrue(flowName.equals(flow.toString()));
        }
        else{
            assertTrue(false);
        }
    }

}
