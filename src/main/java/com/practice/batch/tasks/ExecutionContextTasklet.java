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
public class ExecutionContextTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("ExecutionContextTasklet execute");
        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();

        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

        if (ObjectUtils.isEmpty(jobExecutionContext.get("jobName"))) {
            jobExecutionContext.put("jobName", jobName);
        }

        if (ObjectUtils.isEmpty(stepExecutionContext.get("stepName"))) {
            stepExecutionContext.put("stepName", stepName);
        }

        log.info("jobName: {}", jobExecutionContext.get("jobName"));
        log.info("stepName: {}", stepExecutionContext.get("stepName"));

        return RepeatStatus.FINISHED;
    }
}
