package com.practice.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PassCheckListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution exc) {
        String exitCode = exc.getExitStatus().getExitCode();
        if (!ExitStatus.FAILED.getExitCode().equals(exitCode)) {
            return new ExitStatus("PASS");
        }

        return null;
    }
}
