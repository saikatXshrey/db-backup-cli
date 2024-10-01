package com.shrey.db_backup_cli.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TableStructureEntity {
    private String column_name;
    private String data_type;
    private String column_default;
    private Integer character_maximum_length;
    private Boolean is_nullable;
}
