
package com.example.batchservice.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobExecution;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job bookImportJob;

    public BatchController(
            JobLauncher jobLauncher,
            Job bookImportJob) {
        this.jobLauncher = jobLauncher;
        this.bookImportJob = bookImportJob;
    }

    @PostMapping("/run")
    public Map<String, Object> runBatch() throws Exception {

        JobParameters parameters = new JobParametersBuilder()
                .addLong("run.id", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution =
                jobLauncher.run(bookImportJob, parameters);

        return Map.of(
                "jobName",
                execution.getJobInstance().getJobName(),
                "status",
                execution.getStatus().name(),
                "exitCode",
                execution.getExitStatus().getExitCode()
        );
    }
}
