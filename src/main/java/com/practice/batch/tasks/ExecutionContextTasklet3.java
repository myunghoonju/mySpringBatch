package com.practice.batch.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
public class ExecutionContextTasklet3 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("ExecutionContextTasklet3 execute");
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");
        if (ObjectUtils.isEmpty(name)) {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
            throw new RuntimeException("step3 failure");
        }
        return RepeatStatus.FINISHED;
    }
}
