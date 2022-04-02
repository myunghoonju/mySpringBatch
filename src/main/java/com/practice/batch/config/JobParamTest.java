/*
package com.practice.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JobParamTest implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    // maven package -> terminal -> run command
    // java -jar .\batch-0.0.1-SNAPSHOT.jar "name=user1 seq(long)=2L date(date)=2022/04/02 age(double)=16.5"
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("seq", 2L)
                .addDate("date", new Date())
                .addDouble("age", 16.5)
                .toJobParameters();

        jobLauncher.run(job, params);
    }
}
*/
