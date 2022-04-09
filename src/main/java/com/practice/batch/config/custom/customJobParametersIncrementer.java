package com.practice.batch.config.custom;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class customJobParametersIncrementer implements JobParametersIncrementer{

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public JobParameters getNext(JobParameters param) {
        String id = customJobParametersIncrementer.format.format(new Date());
        return new JobParametersBuilder().addString("run.id", id).toJobParameters();
    }
}
