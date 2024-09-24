package com.shrey.db_backup_cli.request;

import com.shrey.db_backup_cli.constants.argument;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.util.ArgumentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("requestBuilder")
public class RequestBuilder {

    private String host;
    private String username;
    private String password;
    private String db;
    private String destination;

    @Autowired
    private final ApplicationArguments arguments;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBuilder.class);

    public RequestBuilder(final ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    private void validateArguments() {
        Objects.requireNonNull(arguments.getOptionNames(), "Arguments cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.HOST), "Host cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.USERNAME), "Username cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.PASSWORD), "Password cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.DATABASE), "Database cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.DESTINATION), "Destination cannot be null!");
    }

    private void processArguments() {
        for (String optionName : arguments.getOptionNames()) {
            switch (optionName) {
                case argument.HOST -> host = ArgumentParser.parseStringArgument(arguments, optionName);
                case argument.USERNAME -> username = ArgumentParser.parseStringArgument(arguments, optionName);
                case argument.PASSWORD -> password = ArgumentParser.parseStringArgument(arguments, optionName);
                case argument.DATABASE -> db = ArgumentParser.parseStringArgument(arguments, optionName);
                case argument.DESTINATION -> destination = ArgumentParser.parseStringArgument(arguments, optionName);
                default -> LOGGER.info("New parameter {} found needs to be parsed", optionName);
            }
        }
    }

    private RequestEntity build() {
        return RequestEntity
                .builder()
                .host(host)
                .username(username)
                .password(password)
                .database(db)
                .destinationPath(destination)
                .build();
    }

    public RequestEntity buildRequest() {
        LOGGER.info("Received request : [{}]", "db-backup-cli");
        validateArguments();
        processArguments();
        return build();
    }
}
