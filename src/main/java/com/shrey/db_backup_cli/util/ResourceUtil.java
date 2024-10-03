package com.shrey.db_backup_cli.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ResourceUtil {

    @SneakyThrows
    public static String readFileToString(String path) {
        return FileUtils.readFileToString(ResourceUtils.getFile(path),
                StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static void writeStringToFile(String data, String path) {
        // create new file-object
        File file = new File(path);

        //  write to file
        FileUtils.writeStringToFile(
                file,
                data,
                StandardCharsets.UTF_8
        );
    }
}
