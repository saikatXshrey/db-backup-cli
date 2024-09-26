package com.shrey.db_backup_cli;

import com.shrey.db_backup_cli.request.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class
})
public class DbbackupcliApplication
		implements ApplicationRunner {

	@Autowired
	@Qualifier("requestHandler")
	private RequestHandler requestHandler;

	private static final Logger LOGGER = LoggerFactory.getLogger(DbbackupcliApplication.class);

	public static void main(String[] args) {
		SpringApplication
				.run(DbbackupcliApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		requestHandler
				.handleRequest();
		System.exit(0);
	}
}
