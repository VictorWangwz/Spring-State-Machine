package com.victorzhenwang.statemachinedemo.dao;

import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.inject.Inject;

@Repository
public class ApprovalFlowDao {

    private static final String ALL_UNIQUE_WORDS = "all-unique-words";

    private StringRedisTemplate redisTemplate;

    @Inject
    public ApprovalFlowDao(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void addFlowToRedis(ApprovalFlow flow) {
        redisTemplate.opsForValue().set(flow.getId(),flow.toString());
    }
}
