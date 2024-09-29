package com.shrey.db_backup_cli.request;

import com.shrey.db_backup_cli.config.DatabaseSettings;
import com.shrey.db_backup_cli.constants.argument;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.util.ArgumentParserUtil;
import com.shrey.db_backup_cli.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("requestBuilder")
public class RequestBuilder {

    private String host;
    private Integer port;
    private String database;
    private String schema;
    private String username;
    private String password;
    private String type;
    private String destination;

    @Autowired
    private final ApplicationArguments arguments;

    @Autowired
    private DatabaseSettings databaseSettings;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBuilder.class);

    public RequestBuilder(final ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    private void validateArguments() {
        Objects.requireNonNull(arguments.getOptionNames(), "Arguments cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.HOST), "Host cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.PORT), "Port cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.DATABASE), "Database cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.SCHEMA), "Database cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.USERNAME), "Username cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.PASSWORD), "Password cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.TYPE), "Type cannot be null!");
        Objects.requireNonNull(arguments.getOptionValues(argument.DESTINATION), "Destination cannot be null!");
    }

    private void processArguments() {
        for (String optionName : arguments.getOptionNames()) {
            switch (optionName) {
                case argument.HOST -> host = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                case argument.PORT -> port = ArgumentParserUtil.parseIntegerArgument(arguments, optionName);
                case argument.DATABASE -> database = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                case argument.SCHEMA -> schema = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                case argument.USERNAME -> username = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                case argument.PASSWORD -> password = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                case argument.TYPE -> type = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                case argument.DESTINATION -> destination = ArgumentParserUtil.parseStringArgument(arguments, optionName);
                default -> LOGGER.info("New parameter {} found needs to be parsed", optionName);
            }
        }
    }

    private RequestEntity build() {
        RequestEntity request = RequestEntity
                .builder()
                .host(host)
                .port(port)
                .database(database)
                .schema(schema)
                .username(username)
                .password(password)
                .type(type)
                .destinationPath(destination)
                .build();

        // generate jdbcUrl
        request.setUrl(DatabaseUtil
                .configureUrl(databaseSettings, request));

        LOGGER.info("Generated jdbcUrl : [{}]",
                request.getUrl());

        return request;
    }

    public RequestEntity buildRequest() {
        LOGGER.info("Received request : [{}]", "db-backup-cli");
        validateArguments();
        processArguments();
        return build();
    }
}
