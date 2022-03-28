package com.practice.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* init classes by @EnableBatchProcessing in order
*
* 1. SimpleBatchConfiguration.java (proxy)
*
* 2. BatchConfigureConfiguration.java
* 	- BasicBatchConfigure.java
* 	- JpaBatchConfigure.java
*
* 3. BatchAutoConfiguration.java
*/
@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
