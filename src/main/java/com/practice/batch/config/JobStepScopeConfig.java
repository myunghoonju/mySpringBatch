package com.practice.batch.config;

import com.practice.batch.listener.CustomJobListener;
import com.practice.batch.listener.CustomStepListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobStepScopeConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("jobStepScopeConfig")
                .start(step1(null))
                .listener(new CustomJobListener())
                .build();
    }

    @Bean
    @JobScope
    public Step step1(@Value("#{jobParameters['message']}") String message) {
        log.info("message {}", message);

        return stepBuilderFactory.get("step1")
                .tasklet(tasklet1(null))
                .listener(new CustomStepListener())
                .build();
    }

    @Bean
    @JobScope
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(tasklet2(null))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet tasklet1(@Value("#{stepExecutionContext['name']}") String name) {
        return (stepContribution, chunkContext) -> {
            log.info("tasklet1's executed name1  {}", name);
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    @StepScope
    public Tasklet tasklet2(@Value("#{stepExecutionContext['name2']}") String name2) {
        return (stepContribution, chunkContext) -> {
            log.info("tasklet2's executed name2  {}", name2);
            return RepeatStatus.FINISHED;
        };
    }
}
