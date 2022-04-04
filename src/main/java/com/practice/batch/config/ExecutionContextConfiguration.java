/*
package com.practice.batch.config;

import com.practice.batch.tasks.ExecutionContextTasklet;
import com.practice.batch.tasks.ExecutionContextTasklet2;
import com.practice.batch.tasks.ExecutionContextTasklet3;
import com.practice.batch.tasks.ExecutionContextTasklet4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ExecutionContextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ExecutionContextTasklet tasklet1;
    private final ExecutionContextTasklet2 tasklet2;
    private final ExecutionContextTasklet3 tasklet3;
    private final ExecutionContextTasklet4 tasklet4;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .next(step3())
                .next(step4())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(tasklet1).build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet(tasklet2).build();
    }
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet(tasklet3).build();
    }
    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4").tasklet(tasklet4).build();
    }
}
*/
