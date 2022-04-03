/*
package com.practice.batch.config;

import com.practice.batch.tasks.CustomTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
                .next(jobStepThree())
                .build();
    }

    @Bean
    public Step jobStepOne() {
        return stepBuilderFactory.get("jobStepOne")
                .tasklet(new CustomTasklet())
                .build();
    }

    @Bean
    public Step jobStepTwo() {
        return stepBuilderFactory.get("jobStepTwo")
                .tasklet((contribution, chunkContext) -> {
                    log.info("jobStepTwo executed");
                    return RepeatStatus.FINISHED;
                    //throw new RuntimeException("stepTwo failed");
                })
                .build();
    }

    @Bean
    public Step jobStepThree() {
        return stepBuilderFactory.get("jobStepThree")
                .tasklet((contribution, chunkContext) -> {
                    log.info("jobStepThree executed");
                    return RepeatStatus.FINISHED;
                    //throw new RuntimeException("stepTwo failed");
                })
                .build();
    }

}
*/
