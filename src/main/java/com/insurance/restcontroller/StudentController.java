package com.insurance.restcontroller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
@Autowired
private JobLauncher jobLauncher;
@Autowired 
private Job job;

@PostMapping("/importCSV")
public String importCsvToDB() {
	JobParameters jobParameter=new JobParametersBuilder().addLong("startTime",System.currentTimeMillis()).toJobParameters();
	try {
		jobLauncher.run(job, jobParameter);
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
			| JobParametersInvalidException e) {
		throw new RuntimeException(e);
	
	}
	return "CSV Data stored into database succefully...";
}
}
