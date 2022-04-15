package com.practice.batch.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfigurationV3 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job parentJob() {
        return jobBuilderFactory.get("parentJob")
                .start(jobStep(null))
                .on("COMPLETED")
                .to(stepThree())
                .from(jobStep(null))
                .on("FAILED")
                .to(stepTwo())
                .end()
                .build();
    }

    @Bean
    public Step jobStep(JobLauncher launcher) {
        return stepBuilderFactory.get("jobStep")
                .job(childJob())
                .launcher(launcher)
                .parametersExtractor(extractor())
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        stepExecution.getExecutionContext().putString("name", "user1");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    private DefaultJobParametersExtractor extractor() {
        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
        extractor.setKeys(new String[]{"name"});
        return extractor;
    }

    @Bean
    public Job childJob() {
        return jobBuilderFactory.get("childJob")
                .start(stepOne())
                .build();
    }

    @Bean
    public Step stepOne() {
        return stepBuilderFactory.get("stepOne")
                .tasklet((contribution, chunkContext) -> {
                    log.info("stepOne executed");
                    //return RepeatStatus.FINISHED;
                    throw new RuntimeException("stepOne failed");
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step stepTwo() {
        return stepBuilderFactory.get("stepTwo")
                .tasklet((contribution, chunkContext) -> {
                    log.info("stepTwo executed");
                    return RepeatStatus.FINISHED;
                    //throw new RuntimeException("startLimit test");
                })
                .startLimit(6)
                .build();
    }

    @Bean
    public Step stepThree() {
        return stepBuilderFactory.get("stepThree")
                .tasklet((contribution, chunkContext) -> {
                    log.info("stepThree executed");
                    return RepeatStatus.FINISHED;
                    //throw new RuntimeException("startLimit test");
                })
                .build();
    }
}
