package com.practice.batch.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobRepositoryListener implements JobExecutionListener {

    private final JobRepository jobRepo;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }
    // run args :: --name=user1 requestDate=20220404
    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        JobParameters params = new JobParametersBuilder().addString("requestDate", "20220404").toJobParameters();
        JobExecution lastJobExecution = jobRepo.getLastJobExecution(jobName, params);

        if (!ObjectUtils.isEmpty(lastJobExecution)) {
            for (StepExecution exec : lastJobExecution.getStepExecutions()) {
                BatchStatus status = exec.getStatus();
                log.error("status: {}", status);

                ExitStatus exitStatus = exec.getExitStatus();
                log.error("exitStatus: {}", exitStatus);

                String stepName = exec.getStepName();
                log.error("stepName: {}", stepName);
            }
        }
    }
}
