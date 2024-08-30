package com.spring.batch.config;

import com.spring.batch.listener.JobListenerImpl;
import com.spring.batch.listener.StepListenerImpl;
import com.spring.batch.tasklet.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TaskletConfig {

    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private JobListenerImpl jobListener;
    private StepListenerImpl stepListener;
    private SecondTasklet secondTasklet;

    @Autowired
    public TaskletConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager, JobListenerImpl jobListener, StepListenerImpl stepListener, SecondTasklet secondTasklet) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.jobListener = jobListener;
        this.stepListener = stepListener;
        this.secondTasklet = secondTasklet;
    }

    @Bean
    public Job jobOne(Step stepOne) {
        return new JobBuilder("Tasklet Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(stepTwo())
                .listener(jobListener)
                .build();
    }

    @Bean
    public TaskletStep stepOne() {
        return new StepBuilder("Step One", jobRepository)
                .tasklet(taskOne(), transactionManager)
                .listener(stepListener)
                .build();
    }

    private Tasklet taskOne() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("First Task");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public TaskletStep stepTwo() {
        return new StepBuilder("Step Two", jobRepository)
                .tasklet(secondTasklet, transactionManager)
                .listener(stepListener)
                .build();
    }
}