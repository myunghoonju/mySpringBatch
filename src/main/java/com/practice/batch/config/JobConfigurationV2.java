package com.practice.batch.config;


import com.practice.batch.config.validator.CustomJobParameterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfigurationV2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final String[] reqKey = {"date", "name"};
    private static final String[] optKey = {"count"};


    @Bean
    public Job jobConf() {
        return jobBuilderFactory.get("jobConf")
                .start(stepOne())
                .next(stepTwo())
                //.incrementer()
                .preventRestart() // JobInstance already exists and is not restartable
                //.validator(new CustomJobParameterValidator())
                .validator(new DefaultJobParametersValidator(reqKey, optKey))
                //.listener()
                .build();
    }

    @Bean
    public Step stepOne() {
        return stepBuilderFactory.get("stepOne")
                .tasklet((contribution, chunkContext) -> {
                    log.info("stepOne executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step stepTwo() {
        return stepBuilderFactory.get("stepTwo")
                .tasklet((contribution, chunkContext) -> {
                    log.info("stepTwo executed");
                    throw new RuntimeException("preventRestart test");
                })
                .build();
    }
}
