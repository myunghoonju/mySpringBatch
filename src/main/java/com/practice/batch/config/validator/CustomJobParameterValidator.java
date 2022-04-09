package com.practice.batch.config.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters params) throws JobParametersInvalidException {
        if (params == null) {
            throw new JobParametersInvalidException("param is null");
        }
        if (params.getString("name") == null) {
            throw new JobParametersInvalidException("no name param");
        }

    }
}
