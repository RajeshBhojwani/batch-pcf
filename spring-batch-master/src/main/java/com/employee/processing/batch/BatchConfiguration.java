package com.employee.processing.batch;
import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BatchConfiguration {
    private static final Log logger = LogFactory.getLog(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new DepartmentReader().reader();
    }

    @Bean
    public DepartmentProcessor processor() {
        return new DepartmentProcessor();
    }


    @Bean
    public DepartmentWriter writer() {
        return new DepartmentWriter();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepo) {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepo);
        return simpleJobLauncher;
    }

    @Bean
    public Job departmentProcessingJob() {
        return jobBuilderFactory.get("departmentProcessingJob")
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
	public Job job2() {
		return this.jobBuilderFactory.get("job2")
			.start(this.stepBuilderFactory.get("job2step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						logger.info("Job2 was run");
						return RepeatStatus.FINISHED;
					}
				})
				.build())
			.build();
	}



}
