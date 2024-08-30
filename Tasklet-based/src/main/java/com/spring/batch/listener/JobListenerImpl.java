package com.spring.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListenerImpl implements JobExecutionListener {

    private Logger logger = LoggerFactory.getLogger(JobListenerImpl.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Tasklet Job Started...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Tasklet Job Completed...");
        }
    }
}
