package com.shrey.db_backup_cli.request;

import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.processor.RequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("requestHandler")
public class RequestHandler {

    @Autowired
    @Qualifier("requestBuilder")
    private RequestBuilder requestBuilder;

    @Autowired
    @Qualifier("requestProcessor")
    private RequestProcessor requestProcessor;

    public void handleRequest() {
        RequestEntity request = requestBuilder.buildRequest();
        requestProcessor.process(request);
    }
}
