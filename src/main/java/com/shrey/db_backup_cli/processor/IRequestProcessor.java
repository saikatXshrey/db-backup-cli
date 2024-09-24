package com.shrey.db_backup_cli.processor;

import com.shrey.db_backup_cli.entity.RequestEntity;

public interface IRequestProcessor<T extends RequestEntity, R> {
    R process(T request);
}
