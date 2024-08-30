package com.spring.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepListenerImpl implements StepExecutionListener {

    private Logger logger = LoggerFactory.getLogger(StepListenerImpl.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before: " + stepExecution.getStepName());
        System.out.println("Step Execution Context: " + stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("After: " + stepExecution.getStepName());
        System.out.println("Step Execution Context: " + stepExecution.getExecutionContext());
        return null;
    }
}
