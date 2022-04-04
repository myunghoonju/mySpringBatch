package com.practice.batch.config;

import com.practice.batch.listener.JobRepositoryListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobRepositoryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobRepositoryListener jobRepositoryListener;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory
                .get("repoJob")
                .start(stepOne())
                .next(stepTwo())
                //.listener(jobRepositoryListener)
                .build();
    }

    @Bean
    public Step stepOne() {
        return stepBuilderFactory.get("stepOne")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        //sync async test!!!
                        Thread.sleep(5000);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step stepTwo() {
        return stepBuilderFactory.get("stepTwo")
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED)
                .build();
    }
}
