/*
package com.practice.batch.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.Map;

@Slf4j
public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // JobParameters :: preferred
        JobParameters params = contribution.getStepExecution().getJobExecution().getJobParameters();
        Map<String, JobParameter> paramMap = params.getParameters();
        JobParameter name = paramMap.get("name");
        JobParameter seq = paramMap.get("seq");
        JobParameter date = paramMap.get("date");
        JobParameter age = paramMap.get("age");

        // Map :: can't use them, read only
        Map<String, Object> paramFromChunkContext = chunkContext.getStepContext().getJobParameters();

        log.info("jobStepOne executed");
        return RepeatStatus.FINISHED;
    }
}
*/
