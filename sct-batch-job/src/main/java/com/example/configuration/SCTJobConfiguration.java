package com.example.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SCTJobConfiguration {

	private static final Log logger = LogFactory.getLog(SCTJobConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job1() {
		return this.jobBuilderFactory.get("job1")
			.start(this.stepBuilderFactory.get("job1step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						logger.info("Job1 ran successfully");
						return RepeatStatus.FINISHED;
					}
				})
				.build())
			.build();
	}

	@Bean
	public Job job2() {
		return this.jobBuilderFactory.get("job2")
			.start(this.stepBuilderFactory.get("job2step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						logger.info("Job2 ran successfully");
						return RepeatStatus.FINISHED;
					}
				})
				.build())
			.build();
	}
}
