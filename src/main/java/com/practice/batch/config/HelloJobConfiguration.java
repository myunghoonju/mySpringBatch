package com.practice.batch.config;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStepOne())
                .next(helloStepTwo())
                .build();
    }

    @Bean
    public Step helloStepOne() {
        return stepBuilderFactory.get("helloStepOne")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        //biz logic
                        System.out.println("======================");
                        System.out.println(">>> hello spring batch helloStepOne()");
                        System.out.println("======================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step helloStepTwo() {
        return stepBuilderFactory.get("helloStepTwo")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        //biz logic
                        System.out.println("======================");
                        System.out.println(">>> hello spring batch helloStepTwo()");
                        System.out.println("======================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }


}
