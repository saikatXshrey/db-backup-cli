package com.shrey.db_backup_cli.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RequestEntity {
    private String host;
    private String username;
    private String password;
    private String database;
    private String destinationPath;
}
