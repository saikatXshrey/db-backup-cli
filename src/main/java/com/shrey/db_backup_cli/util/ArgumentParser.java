package com.shrey.db_backup_cli.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ArgumentParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentParser.class);

    public static String parseStringArgument(ApplicationArguments arguments, String optionName) {
        List<String> optionValues = arguments.getOptionValues(optionName);
        String value = null;

        if (!CollectionUtils.isEmpty(optionValues)) {
            value = optionValues.get(0);
        }

        LOGGER.info("Parsed {} : [{}]", optionName, value);

        return value;
    }
}
