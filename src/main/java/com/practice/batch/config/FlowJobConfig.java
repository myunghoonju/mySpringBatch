package com.practice.batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FlowJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    //@Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob")
                .start(flowA())
                .next(step3())
                .next(flowB())
                .next(step6())
                .end()
                .build();
    }

    @Bean
    public Flow flowA() {
        FlowBuilder<Flow> builder = new FlowBuilder<>("flowA");
        builder
                .start(step1())
                .next(step2())
                .end();
        return builder.build();
    }

    //@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step1 executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

   // @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step2 executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

  //  @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step3 executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

  //  @Bean
    public Flow flowB() {
        FlowBuilder<Flow> builder = new FlowBuilder<>("flowB");
        builder
                .start(step4())
                .next(step5())
                .end();
        return builder.build();
    }

//    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step4 executed");
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step5 executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

   // @Bean
    public Step step6() {
        return stepBuilderFactory.get("step6")
                .tasklet((contribution, chunkContext) -> {
                    log.info("step6 executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }



}
