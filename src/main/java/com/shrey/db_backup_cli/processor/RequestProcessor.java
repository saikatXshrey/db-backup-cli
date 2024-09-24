package com.shrey.db_backup_cli.processor;

import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.entity.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("requestProcessor")
public class RequestProcessor implements
        IRequestProcessor<RequestEntity, ResponseEntity> {

    @Override
    public ResponseEntity process(RequestEntity request) {
        return null;
    }
}
