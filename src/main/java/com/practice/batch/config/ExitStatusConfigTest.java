package com.practice.batch.config;

import com.practice.batch.listener.PassCheckListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
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
public class ExitStatusConfigTest {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job exitBatchJob() {
        return jobBuilderFactory.get("exitBatchJob")
                .start(exitStep1())
                    .on("FAILED")
                    .to(exitStep2())
                    .on("PASS")
                    .stop()
                .end()
                .build();
    }

    @Bean
    public Step exitStep1() {
        return stepBuilderFactory.get("exitStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("exitStep1 executed");
                    contribution.getStepExecution().setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step exitStep2() {
        return stepBuilderFactory.get("exitStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("exitStep2 executed");
                    return RepeatStatus.FINISHED;
                })
                .listener(new PassCheckListener())
                .build();
    }
}
