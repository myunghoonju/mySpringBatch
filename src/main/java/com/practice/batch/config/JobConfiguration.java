package com.practice.batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobConf() {
        return jobBuilderFactory.get("jobConf")
                .start(jobStepOne())
                .next(jobStepTwo())
                .build();
    }

    @Bean
    public Step jobStepOne() {
        return stepBuilderFactory.get("jobStepOne")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) ->{
                        log.info("jobStepOne executed");
                        return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step jobStepTwo() {
        return stepBuilderFactory.get("jobStepTwo")
                .tasklet((contribution, chunkContext) -> {
                    log.info("jobStepTwo executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
