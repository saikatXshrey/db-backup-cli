package com.shrey.db_backup_cli.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RequestEntity {
    private String host;
    private int port;
    private String database;
    private String schema;
    private String url;
    private String username;
    private String password;
    private String type;
    private String destinationPath;
}
