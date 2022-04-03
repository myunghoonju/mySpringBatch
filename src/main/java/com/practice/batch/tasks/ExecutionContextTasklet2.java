package com.practice.batch.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
public class ExecutionContextTasklet2 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("ExecutionContextTasklet2 execute");
        ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();

        log.info("jobName: {}", jobExecutionContext.get("jobName"));
        log.info("stepName: {}", stepExecutionContext.get("stepName"));

        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();
        if (ObjectUtils.isEmpty(stepExecutionContext.get("stepName"))) {
            stepExecutionContext.put("stepName", stepName);
        }

        return RepeatStatus.FINISHED;
    }
}
