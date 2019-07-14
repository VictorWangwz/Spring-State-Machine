package com.victorzhenwang.statemachinedemo.config;

import com.victorzhenwang.statemachinedemo.model.ApprovalFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

@Configuration
@ComponentScan(basePackages = "com.victorzhenwang.statemachinedemo")
public class PersistConfig {

    @Autowired
    private InMemoryStateMachinePersist inMemoryStateMachinePersist;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ApprovalFlowStateMachinePersist approvalFlowStateMachinePersist;

    @Bean(name="approvalFlowInMemoryPersister")
    public StateMachinePersister<States, Events, String> getPersister() {
        return new DefaultStateMachinePersister<>(inMemoryStateMachinePersist);
    }

    @Bean(name = "approvalFlowRedisPersister")
    public RedisStateMachinePersister<States, Events> redisPersister() {
        return new RedisStateMachinePersister<>(redisPersist());
    }

    public StateMachinePersist<States, Events,String> redisPersist() {
        RedisStateMachineContextRepository<States, Events> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }

    @Bean
    public StringRedisTemplate redisTemplate(){
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean(name="approvalFlowPersister")
    public StateMachinePersister<States, Events, ApprovalFlow> orderPersister() {
        return new DefaultStateMachinePersister<States, Events, ApprovalFlow>(approvalFlowStateMachinePersist);
    }
}
